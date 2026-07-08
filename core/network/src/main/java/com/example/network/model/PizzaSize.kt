package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaSize(
    val price: Double,
    val type: Size
)
