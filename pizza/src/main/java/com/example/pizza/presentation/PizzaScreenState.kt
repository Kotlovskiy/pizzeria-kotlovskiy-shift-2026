package com.example.pizza.presentation

import com.example.pizza.domain.DoughInfo
import com.example.pizza.domain.Ingredient
import com.example.pizza.domain.PizzaInfo
import com.example.pizza.domain.SizeInfo
import com.example.pizza.domain.Topping

sealed interface PizzaScreenState {
    object Initial : PizzaScreenState
    object LoadingContent : PizzaScreenState
    data class Error(val error: PizzaScreenError) : PizzaScreenState
    data class Content(
        val pizzaInfo: PizzaInfo,
        val selectedDough: DoughInfo,
        val selectedSize: SizeInfo,
        val allToppings: List<Topping>,
        val selectedToppings: List<Ingredient>,
        val isEditMode: Boolean = false,
        val loading: Boolean = false
    ) : PizzaScreenState
    object Navigation : PizzaScreenState
}
