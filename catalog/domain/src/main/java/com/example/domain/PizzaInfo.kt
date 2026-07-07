package com.example.domain

import kotlinx.serialization.Serializable

@Serializable
data class PizzaInfo(
    val id: String,
    val image: String,
    val title: String,
    val description: String,
    val minPrice: Int,
    val sizes: List<SizeInfo>,
    val toppings: List<Topping>,
    val doughs: List<DoughInfo>
)
