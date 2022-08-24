package com.bmc.data.repository.logIn.source.remote

import com.bmc.data.repository.logIn.model.AuthEntity
import com.bmc.data.repository.logIn.model.mapper.toEntity
import com.bmc.data.repository.logIn.source.LogInDataSource
import com.bmc.domain.logIn.model.User
import com.bmc.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogInRemoteDataSource : LogInDataSource {

    override suspend fun logIn(user: User): Result<AuthEntity> {
        return withContext(Dispatchers.IO) {
            if (validateUser(user)) {
                Result.Success(user.toEntity())
            } else {
                Result.Error(message = "Usuario y/o contraseña incorrectos")
            }
        }
    }

    private fun validateUser(user: User): Boolean {
        return user.documentNumber == "44444444" && user.password == "111111"
    }
}