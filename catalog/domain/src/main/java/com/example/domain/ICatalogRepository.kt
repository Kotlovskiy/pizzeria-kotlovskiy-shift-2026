package com.example.domain

interface ICatalogRepository {
    suspend fun getPizzas() : List<PizzaInfo>
}
