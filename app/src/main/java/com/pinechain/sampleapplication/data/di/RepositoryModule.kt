package com.pinechain.sampleapplication.data.di

import com.pinechain.sampleapplication.data.repository.UserRepository
import com.pinechain.sampleapplication.data.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get())}
}