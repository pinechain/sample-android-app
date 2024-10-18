package com.pinechain.sampleapplication.data.repository

import com.pinechain.sampleapplication.data.service.UserAPIService
import kotlinx.coroutines.flow.Flow

// Here we would handle network errors, retries, etc. if we had a more complex scenario.
class UserRepositoryImpl(private val apiService: UserAPIService) : UserRepository {
    override suspend fun login(username: String, password: String): Flow<Result<Boolean>> {
        return apiService.validateLogin(username, password)
    }
}