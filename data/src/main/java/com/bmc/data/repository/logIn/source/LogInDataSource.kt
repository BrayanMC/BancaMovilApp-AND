package com.bmc.data.repository.logIn.source

import com.bmc.data.repository.logIn.model.AuthEntity
import com.bmc.domain.logIn.model.User
import com.bmc.domain.util.Result

interface LogInDataSource {
    suspend fun logIn(user: User): Result<AuthEntity>
}