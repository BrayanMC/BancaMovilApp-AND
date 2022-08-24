package com.bmc.login.screens.credentials.model

data class LogInCredentialsForm(
    val isValidDocumentNumber: Boolean = false,
    val isValidPassword: Boolean = false,
    val errorMessage: String = ""
)