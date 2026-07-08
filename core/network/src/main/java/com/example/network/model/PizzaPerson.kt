package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaPerson(
    val firstname: String,
    val lastname: String,
    val middleName: String,
    val phone: String
)
