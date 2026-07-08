package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileProfileDto(
    val city: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null
)
