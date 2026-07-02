package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CancelPizzaOrderDto(
    val orderId: String
)
