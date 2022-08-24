package com.bmc.domain.util

object ErrorCodes {

    const val SESSION_EXPIRED = "session_expired"
    const val SESSION_TIMEOUT = "session_timeout"
    const val ANOTHER_SESSION_LOGGED = "user_already_logged"

    // GRPC Exceptions
    const val RESPONSE_TIMEOUT = "DEADLINE_EXCEEDED"

    // UNAVAILABLE. It happens when the connection is lost during a call, is a transient condition which can be corrected by retrying with a backoff
    const val UNAVAILABLE = "UNAVAILABLE"
}
