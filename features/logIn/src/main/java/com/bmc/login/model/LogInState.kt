package com.bmc.login.model

import com.bmc.common.liveData.SingleEvent
import com.bmc.core.base.BaseViewState

data class LogInState(
    val isLoading: Boolean = false,
    val navigateToHome: SingleEvent<Unit>? = null,
) : BaseViewState