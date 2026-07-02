package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CreatePizzaPaymentDebitCardDto(
    val cvv: String,
    val expireDate: String,
    val pan: String
)
