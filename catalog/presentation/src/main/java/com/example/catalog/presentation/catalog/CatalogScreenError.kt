package com.example.catalog.presentation.catalog

sealed interface CatalogScreenError {
    object NoInternet : CatalogScreenError
    object ServerError : CatalogScreenError
    object SmtWentWrong : CatalogScreenError
}
