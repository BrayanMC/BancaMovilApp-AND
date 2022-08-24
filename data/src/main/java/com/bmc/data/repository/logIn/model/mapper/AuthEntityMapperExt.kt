package com.bmc.data.repository.logIn.model.mapper

import com.bmc.data.repository.logIn.model.AuthEntity
import com.bmc.domain.logIn.model.Auth
import com.bmc.domain.logIn.model.User

fun AuthEntity.toDomain() = Auth(documentNumber = documentNumber)

fun User.toEntity() = AuthEntity(documentNumber = documentNumber)