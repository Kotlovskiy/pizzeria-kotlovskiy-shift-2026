package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaAddress(
    val apartment: String,
    val comment: String,
    val house: String,
    val street: String
)
