package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class OrderedPizza(
    val dough: Dough,
    val id: String,
    val size: Size,
    val toppings: List<Ingredient>
)
