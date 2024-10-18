package com.pinechain.sampleapplication.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.pinechain.sampleapplication.core.model.ScreenState
import com.pinechain.sampleapplication.core.presentation.activity.BaseActivity
import com.pinechain.sampleapplication.data.di.repositoryModule
import com.pinechain.sampleapplication.data.di.serviceModule
import com.pinechain.sampleapplication.databinding.ActivityMainBinding
import com.pinechain.sampleapplication.domain.di.useCaseModule
import com.pinechain.sampleapplication.presentation.di.viewModelModule
import com.pinechain.sampleapplication.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startDI()
        setupLayout()
        setCollectors()
        setListeners()
    }

    private fun startDI() = startKoin {
        androidContext(this@MainActivity)
        modules(
            serviceModule,
            repositoryModule,
            useCaseModule,
            viewModelModule,
        )
    }

    private fun setupLayout() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setCollectors() = lifecycleScope.launch {
        collectState()
    }

    private fun setListeners() {
        setLoginButton()
    }

    private suspend fun collectState(): Nothing = viewModel.state.collect {
        when (it) {
            ScreenState.Idle -> {
                finishLoading()
                hideError()
                toggleLoginButton(true)
            }

            is ScreenState.Finished -> {
                finishLoading()
                toggleLoginButton(it.isError)
                if (it.isError) {
                    showError(it.data)
                } else {
                    hideError()
                    showLoginSuccess()
                }

            }

            ScreenState.Loading -> {
                hideError()
                startLoading()
                toggleLoginButton(false)
            }
        }
    }

    private fun showLoginSuccess() = binding.successTextView.run {
        visibility = View.VISIBLE
    }

    private fun toggleLoginButton(isActive: Boolean) = binding.loginButton.run {
        isEnabled = isActive
    }

    private fun showError(message: String?) = binding.errorTextView.run {
        text = message
        visibility = View.VISIBLE
    }

    private fun hideError() = binding.errorTextView.run {
        visibility = View.GONE
    }

    private fun startLoading() = binding.progressBar.run {
        visibility = View.VISIBLE
    }

    private fun finishLoading() = binding.progressBar.run {
        visibility = View.GONE
    }

    private fun setLoginButton() = binding.loginButton.run {
        setOnClickListener {
            viewModel.login(
                binding.usernameEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }
}