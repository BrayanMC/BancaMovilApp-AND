package com.bmc.domain.logIn.usecase

import com.bmc.domain.logIn.model.User
import com.bmc.domain.logIn.repository.LogInRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val logInRepository: LogInRepository
) {

    suspend fun logIn(user: User) = logInRepository.logIn(user)
}
