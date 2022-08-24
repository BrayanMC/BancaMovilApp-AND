package com.bmc.login.screens.credentials.model

import com.bmc.common.liveData.SingleEvent
import com.bmc.core.base.BaseViewState

data class LogInCredentialsState(
    val isLoading: Boolean = false,
    val user: UserModel = UserModel(),
    val form: LogInCredentialsForm = LogInCredentialsForm(),
    val onLogInSuccess: SingleEvent<Unit>? = null,
) : BaseViewState