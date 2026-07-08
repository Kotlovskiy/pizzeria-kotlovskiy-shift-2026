package com.example.catalog.presentation

import com.example.domain.PizzaInfo

sealed interface CatalogScreenState {
    object Initial : CatalogScreenState
    object Loading : CatalogScreenState
    data class Error(val error: CatalogScreenError) : CatalogScreenState
    object Empty : CatalogScreenState
    data class Success(val pizzas: List<PizzaInfo>) : CatalogScreenState
}
