package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SessionResponse(
    val success: Boolean,
    val user: User,
    val reason: String? = null
)
