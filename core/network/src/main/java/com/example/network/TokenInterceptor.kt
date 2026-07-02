package com.example.network

import com.example.network.NetworkConstants.AUTHORIZATION_HEADER
import com.example.network.NetworkConstants.BEARER
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = runBlocking { tokenManager.getToken() }

        val request = originalRequest.newBuilder()
            .header(
                AUTHORIZATION_HEADER,
                "$BEARER $token"
            )
            .build()

        return chain.proceed(request)
    }
}
