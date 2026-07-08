package com.example.pizza.domain

import kotlinx.serialization.Serializable

@Serializable
data class Topping(
    val image: String,
    val price: Int,
    val type: Ingredient
)
