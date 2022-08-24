package com.bmc.domain.logIn.repository

import com.bmc.domain.logIn.model.Auth
import com.bmc.domain.logIn.model.User
import com.bmc.domain.util.Result

interface LogInRepository {
    suspend fun logIn(user: User): Result<Auth>
}
