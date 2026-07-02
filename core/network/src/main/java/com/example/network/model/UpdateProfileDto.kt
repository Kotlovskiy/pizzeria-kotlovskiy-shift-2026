package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileDto(
    val phone: String,
    val profile: UpdateProfileProfileDto
)
