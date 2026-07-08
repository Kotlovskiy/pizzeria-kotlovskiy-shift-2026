package com.example.catalog.presentation

sealed interface CatalogScreenError {
    object NoInternet : CatalogScreenError
    object ServerError : CatalogScreenError
    object SmtWentWrong : CatalogScreenError
}
