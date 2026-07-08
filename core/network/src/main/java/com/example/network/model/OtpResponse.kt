package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class OtpResponse(
    val retryDelay: Int,
    val success: Boolean,
    val reason: String? = null
)
