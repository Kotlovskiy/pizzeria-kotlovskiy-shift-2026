package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CreatePizzaPaymentPersonDto(
    val firstname: String,
    val lastname: String,
    val phone: String,
    val middleName: String? = null
)
