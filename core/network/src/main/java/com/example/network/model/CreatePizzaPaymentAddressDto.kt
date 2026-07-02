package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CreatePizzaPaymentAddressDto(
    val apartment: String,
    val house: String,
    val street: String,
    val comment: String? = null
)
