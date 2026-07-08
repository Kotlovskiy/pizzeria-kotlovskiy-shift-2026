package com.example.pizza.domain

import kotlinx.serialization.Serializable

@Serializable
data class Pizza(
    val id: String,
    val image: String,
    val title: String,
    val size: SizeInfo,
    val dough: DoughInfo,
    val toppings: List<Topping>,
    val count: Int
)
