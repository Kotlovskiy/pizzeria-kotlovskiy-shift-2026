package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaDough(
    val price: Double,
    val type: Dough
)
