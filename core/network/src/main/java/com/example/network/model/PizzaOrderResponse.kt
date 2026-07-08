package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaOrderResponse(
    val order: PizzaOrder,
    val success: Boolean,
    val reason: String? = null
)
