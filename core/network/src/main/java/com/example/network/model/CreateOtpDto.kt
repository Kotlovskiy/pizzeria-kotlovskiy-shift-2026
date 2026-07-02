package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateOtpDto(
    val phone: String
)
