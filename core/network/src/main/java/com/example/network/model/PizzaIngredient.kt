package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaIngredient(
    val img: String,
    val price: Double,
    val type: Ingredient
)
