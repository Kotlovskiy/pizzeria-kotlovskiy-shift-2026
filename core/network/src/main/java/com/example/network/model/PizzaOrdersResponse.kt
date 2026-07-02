package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaOrdersResponse(
    val orders: List<PizzaOrder>,
    val success: Boolean,
    val reason: String? = null
)
