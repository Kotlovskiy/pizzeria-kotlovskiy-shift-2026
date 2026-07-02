package com.example.network.api

import com.example.network.model.SessionResponse
import com.example.network.model.UpdateProfileDto
import com.example.network.model.UpdateProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserApi {
    @PATCH
    fun updateProfile(@Body updateProfileDto: UpdateProfileDto): Response<UpdateProfileResponse>

    @GET
    fun getSession(): Response<SessionResponse>
}
