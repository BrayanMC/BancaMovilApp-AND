package com.bmc.login.screens.credentials.model.mapper

import com.bmc.domain.logIn.model.User
import com.bmc.login.screens.credentials.model.UserModel

fun UserModel.toDomain() =
    User(
        documentNumber = documentNumber,
        password = password
    )