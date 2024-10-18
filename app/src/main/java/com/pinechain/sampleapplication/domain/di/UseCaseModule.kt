package com.pinechain.sampleapplication.domain.di

import com.pinechain.sampleapplication.domain.usecase.LoginUseCase
import com.pinechain.sampleapplication.domain.usecase.LoginUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<LoginUseCase> { LoginUseCaseImpl(get()) }
}