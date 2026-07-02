package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Pizza(
    val allergens: List<String>,
    val calories: Double,
    val carbohydrates: String,
    val description: String,
    val doughs: List<PizzaDough>,
    val id: String,
    val img: String,
    val ingredients: List<PizzaIngredient>,
    val isGlutenFree: Boolean,
    val isHit: Boolean,
    val isNew: Boolean,
    val isVegetarian: Boolean,
    val name: String,
    val protein: String,
    val sizes: List<PizzaSize>,
    val sodium: String,
    val toppings: List<PizzaIngredient>,
    val totalFat: String
)
