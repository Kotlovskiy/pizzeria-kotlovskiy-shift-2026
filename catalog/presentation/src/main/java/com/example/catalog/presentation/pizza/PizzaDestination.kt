package com.example.catalog.presentation.pizza

import androidx.navigation3.runtime.NavKey
import com.example.domain.Pizza
import kotlinx.serialization.Serializable

@Serializable
data class PizzaDestination(
    val pizzaId: String,
    val pizza: Pizza? = null
) : NavKey
