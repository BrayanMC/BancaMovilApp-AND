package com.bmc.common.utils

class CommonUtils {

    init {
        throw IllegalStateException("Utility class")
    }

    companion object {

        fun isValidDocumentNumber(target: String?): Boolean {
            target?.let {
                return it.trim().length == 8
            }
            return false
        }

        fun isValidPassword(target: String?): Boolean {
            target?.trim()?.let {
                return it.isNotEmpty() && it.length == 6
            }
            return false
        }
    }
}
