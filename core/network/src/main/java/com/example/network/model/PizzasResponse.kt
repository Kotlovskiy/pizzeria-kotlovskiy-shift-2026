package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzasResponse(
    val catalog: List<Pizza>,
    val success: Boolean,
    val reason: String? = null
)
