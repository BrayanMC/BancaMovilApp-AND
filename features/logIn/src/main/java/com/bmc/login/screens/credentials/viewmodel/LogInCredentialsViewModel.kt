package com.bmc.login.screens.credentials.viewmodel

import androidx.lifecycle.viewModelScope
import com.bmc.common.liveData.SingleEvent
import com.bmc.common.utils.CommonUtils
import com.bmc.core.base.BaseViewModel
import com.bmc.domain.logIn.model.Auth
import com.bmc.domain.logIn.usecase.LogInUseCase
import com.bmc.domain.util.Result
import com.bmc.login.screens.credentials.model.LogInCredentialsState
import com.bmc.login.screens.credentials.model.mapper.toDomain
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInCredentialsViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase
) : BaseViewModel<LogInCredentialsState>() {

    override val initialState = LogInCredentialsState()

    init {
        state = initialState
    }

    fun onDocumentNumberTextChanged(text: CharSequence?) {
        val documentNumber = text?.toString()?.trim() ?: ""
        val currentState = state.copy(
            user = state.user.copy(documentNumber = documentNumber)
        )
        val formState = currentState.form.copy(
            isValidDocumentNumber = false,
            errorMessage = ""
        )
        stateAsync = currentState.copy(form = formState)
    }

    fun onPasswordTextChanged(text: CharSequence?) {
        val password = text?.toString()?.trim() ?: ""
        val currentState = state.copy(
            user = state.user.copy(password = password)
        )
        val formState = currentState.form.copy(
            isValidPassword = false,
            errorMessage = ""
        )
        stateAsync = currentState.copy(form = formState)
    }

    fun logIn() {
        when {
            !isValidDocumentNumber(state) -> {
                stateAsync = state.copy(
                    form = state.form.copy(
                        isValidDocumentNumber = false,
                        errorMessage = "Ingrese el DNI"
                    )
                )
            }
            !isValidPassword(state) -> {
                stateAsync =
                    state.copy(
                        form = state.form.copy(
                            isValidPassword = false,
                            errorMessage = "Ingrese la contraseña"
                        )
                    )
            }
            else -> {
                viewModelScope.launch {
                    val result = logInUseCase.logIn(user = state.user.toDomain())
                    delay(500)
                    handleOnLogInResult(result)
                }
            }
        }
    }

    private fun handleOnLogInResult(result: Result<Auth>) {
        when (result) {
            is Result.Success -> {
                stateAsync = state.copy(
                    onLogInSuccess = SingleEvent(Unit)
                )
            }
            is Result.Error -> {
                stateAsync =
                    state.copy(
                        form = state.form.copy(
                            isValidPassword = false,
                            errorMessage = result.message
                        )
                    )
            }
        }
    }

    private fun isValidDocumentNumber(currentState: LogInCredentialsState = state) =
        CommonUtils.isValidDocumentNumber(currentState.user.documentNumber)

    private fun isValidPassword(currentState: LogInCredentialsState = state) =
        CommonUtils.isValidPassword(currentState.user.password)
}
