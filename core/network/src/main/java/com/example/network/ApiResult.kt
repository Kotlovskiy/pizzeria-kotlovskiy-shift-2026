package com.example.network

import retrofit2.Response
import java.io.IOException

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class HttpError<T>(val code: Int, val message: String, val body: String?) : ApiResult<T>()
    data class NetworkException(val error: Throwable) : ApiResult<Nothing>()
}

fun <T> Response<T>.toApiResult(): ApiResult<T> {
    return try {
        if (isSuccessful) {
            body()?.let { responseBody ->
                ApiResult.Success(responseBody)
            } ?: ApiResult.HttpError(
                code = code(),
                message = message() ?: "",
                body = null
            )
        } else {
            ApiResult.HttpError(
                code = code(),
                message = message(),
                body = errorBody()?.string()
            )
        }
    } catch (e: IOException) {
        ApiResult.NetworkException(e)
    }
}

fun Response<Unit>.toUnitApiResult(): ApiResult<Unit> {
    return try {
        if (isSuccessful) {
            ApiResult.Success(Unit)
        } else {
            ApiResult.HttpError(
                code = code(),
                message = message(),
                body = errorBody()?.string()
            )
        }
    } catch (e: IOException) {
        ApiResult.NetworkException(e)
    }
}
