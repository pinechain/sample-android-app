package com.pinechain.sampleapplication.data.di

import com.pinechain.sampleapplication.data.service.MockUserAPIServiceImpl
import com.pinechain.sampleapplication.data.service.UserAPIService
import org.koin.dsl.module

val serviceModule = module {
    single<UserAPIService> { MockUserAPIServiceImpl() }
}