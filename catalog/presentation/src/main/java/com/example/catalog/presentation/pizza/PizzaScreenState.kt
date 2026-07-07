package com.example.catalog.presentation.pizza

import com.example.catalog.presentation.catalog.CatalogScreenError
import com.example.domain.DoughInfo
import com.example.domain.Ingredient
import com.example.domain.PizzaInfo
import com.example.domain.SizeInfo
import com.example.domain.Topping

sealed interface PizzaScreenState {
    object Initial : PizzaScreenState
    object LoadingContent : PizzaScreenState
    data class Error(val error: CatalogScreenError) : PizzaScreenState
    data class Success(
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
