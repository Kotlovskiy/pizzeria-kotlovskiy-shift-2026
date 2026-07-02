package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SignInDto(
    val code: Int,
    val phone: String
)
