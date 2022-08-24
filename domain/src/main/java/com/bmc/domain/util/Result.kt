package com.bmc.domain.util

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(
        val internalCode: String = "",
        val messageCode: String = "",
        val message: String = ""
    ) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[internalCode=$internalCode, messageCode=$messageCode, message=$message"
        }
    }
}
