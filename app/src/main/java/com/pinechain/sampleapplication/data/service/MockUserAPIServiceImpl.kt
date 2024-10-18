package com.pinechain.sampleapplication.data.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

// Here we would communicate with an external API using retrofit and OKHttp.
// For the sake of simplicity, we are implementing a mock API here.
class MockUserAPIServiceImpl : UserAPIService {
    override suspend fun validateLogin(username: String, password: String): Flow<Result<Boolean>> {
        return when (username) {
            FAILONE_USR -> flowOf(Result.failure(Exception("Username outdated, reach out to support")))
            FAILTWO_USR -> flowOf(Result.failure(Exception("Invalid password")))
            SUCCESS_USR -> validatePassword(password)
            else -> flowOf(Result.failure(Exception("Invalid username")))
        }
    }

    private fun validatePassword(password: String): Flow<Result<Boolean>> {
        if (password == SUCCESS_PWD) return flowOf(Result.success(true))
        else return flowOf(Result.failure(Exception("Invalid password")))
    }

    companion object {
        private const val SUCCESS_USR = "success"
        private const val SUCCESS_PWD = "pwd123"
        private const val FAILONE_USR = "failone"
        private const val FAILTWO_USR = "failtwo"
    }
}