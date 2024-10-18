package com.pinechain.sampleapplication.domain.usecase

import com.pinechain.sampleapplication.core.model.ResultState
import com.pinechain.sampleapplication.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// If we had a more complex scenario, this is where business rules should come into play.
class LoginUseCaseImpl(private val userRepository: UserRepository) : LoginUseCase {
    override suspend fun login(username: String, password: String): Flow<ResultState> {
        return userRepository.login(username, password).map { result ->
            if (result.isSuccess) ResultState.Success()
            else ResultState.Failure(result.exceptionOrNull()?.message)
        }
    }
}