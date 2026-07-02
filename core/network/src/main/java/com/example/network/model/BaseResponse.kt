package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    val success: Boolean,
    val reason: String? = null
)
