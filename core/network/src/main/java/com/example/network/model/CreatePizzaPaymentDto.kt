package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CreatePizzaPaymentDto(
    val debitCard: CreatePizzaPaymentDebitCardDto,
    val person: CreatePizzaPaymentPersonDto,
    val pizzas: List<OrderedPizza>,
    val receiverAddress: CreatePizzaPaymentAddressDto
)
