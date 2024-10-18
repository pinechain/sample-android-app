package com.pinechain.sampleapplication.data.service

import kotlinx.coroutines.flow.Flow

interface UserAPIService {
    suspend fun validateLogin(username: String, password: String): Flow<Result<Boolean>>
}