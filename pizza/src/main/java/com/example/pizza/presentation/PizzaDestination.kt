package com.example.pizza.presentation

import androidx.navigation3.runtime.NavKey
import com.example.pizza.domain.Pizza
import kotlinx.serialization.Serializable

@Serializable
data class PizzaDestination(
    val pizzaId: String,
    val pizza: Pizza? = null
) : NavKey
