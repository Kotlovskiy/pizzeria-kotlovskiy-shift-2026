package com.example.domain

interface ICatalogRepository {
    suspend fun getPizzas() : List<PizzaInfo>

    suspend fun getPizzaInfoById(id: String) : PizzaInfo

    suspend fun addPizza(pizza: Pizza)

    suspend fun updatePizza(
        pizza: Pizza,
        size: SizeInfo? = null,
        dough: DoughInfo? = null,
        toppings: List<Topping>? = null
    )
}
