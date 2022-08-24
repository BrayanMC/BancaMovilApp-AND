package com.bmc.login.viewmodel

import com.bmc.common.liveData.SingleEvent
import com.bmc.core.base.BaseViewModel
import com.bmc.login.model.LogInState

class LogInViewModel : BaseViewModel<LogInState>() {

    override val initialState = LogInState()

    init {
        state = initialState
    }

    fun onLogInSuccess() {
        state = state.copy(navigateToHome = SingleEvent(Unit))
    }
}