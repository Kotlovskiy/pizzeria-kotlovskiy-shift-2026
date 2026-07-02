package com.example.network.api

import com.example.network.model.CreateOtpDto
import com.example.network.model.OtpResponse
import com.example.network.model.SignInDto
import com.example.network.model.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST
    fun createOtp(@Body createOtpDto: CreateOtpDto): Response<OtpResponse>

    @POST
    fun signIn(@Body signInDto: SignInDto): Response<SignInResponse>
}
