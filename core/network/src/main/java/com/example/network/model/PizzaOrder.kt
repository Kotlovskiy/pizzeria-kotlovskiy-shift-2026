package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaOrder(
    val id: String,
    val cancellable: Boolean,
    val person: PizzaPerson,
    val pizzas: List<Pizza>,
    val receiverAddress: PizzaAddress,
    val status: PizzaStatus,
    val totalPrice: Double
)
