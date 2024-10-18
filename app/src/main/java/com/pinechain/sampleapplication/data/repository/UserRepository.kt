package com.pinechain.sampleapplication.data.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(username: String, password: String): Flow<Result<Boolean>>
}