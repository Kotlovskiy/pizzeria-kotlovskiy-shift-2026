package com.example.errors

fun Int.toNetworkException() =
    when (this) {
        401 -> UnauthorizedException()
        in 500..599 -> ServerErrorException()
        else -> SmtWentWrongException()
    }
