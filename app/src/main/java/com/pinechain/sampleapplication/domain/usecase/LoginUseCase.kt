package com.pinechain.sampleapplication.domain.usecase

import com.pinechain.sampleapplication.core.model.ResultState
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend fun login(username: String, password: String): Flow<ResultState>
}