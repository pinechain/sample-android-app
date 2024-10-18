package com.pinechain.sampleapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinechain.sampleapplication.core.model.ResultState
import com.pinechain.sampleapplication.core.model.ScreenState
import com.pinechain.sampleapplication.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _state: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Idle)
    val state = _state.asStateFlow()

    fun login(username: String, password: String) = viewModelScope.launch {
        _state.value = ScreenState.Loading

        loginUseCase.login(username, password).collect { result ->
            when (result) {
                is ResultState.Failure -> _state.value = ScreenState.Finished(result.resultData, true)
                is ResultState.Success -> _state.value = ScreenState.Finished()
            }
        }
    }
}