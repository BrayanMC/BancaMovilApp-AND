package com.bmc.data.repository.logIn

import com.bmc.data.repository.logIn.model.mapper.toDomain
import com.bmc.data.repository.logIn.source.LogInDataSource
import com.bmc.domain.logIn.model.Auth
import com.bmc.domain.logIn.model.User
import com.bmc.domain.logIn.repository.LogInRepository
import com.bmc.domain.util.Result
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(
    private val logInDataSource: LogInDataSource
) : LogInRepository {

    override suspend fun logIn(user: User): Result<Auth> {
        return when (val result = logInDataSource.logIn(user)) {
            is Result.Success -> Result.Success(result.data.toDomain())
            is Result.Error -> result
        }
    }
}