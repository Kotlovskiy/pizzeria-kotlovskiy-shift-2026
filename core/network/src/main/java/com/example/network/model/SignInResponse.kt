package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val success: Boolean,
    val token: String,
    val user: User,
    val reason: String? = null
)
