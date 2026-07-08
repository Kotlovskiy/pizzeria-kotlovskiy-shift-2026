package com.example.pizza.data

import com.example.errors.SmtWentWrongException
import com.example.pizza.domain.DoughInfo
import com.example.pizza.domain.Pizza
import com.example.pizza.domain.PizzaInfo
import com.example.pizza.domain.PizzaRepository
import com.example.pizza.domain.SizeInfo
import com.example.pizza.domain.Topping
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource
) : PizzaRepository {
    override suspend fun getPizzaInfoById(id: String): PizzaInfo {
        val localResult = localDataSource.getPizzaInfoById(id)
        if (localResult != null) return localResult
        throw SmtWentWrongException()
    }

    override suspend fun addPizza(pizza: Pizza) {
        localDataSource.addPizza(pizza)
    }

    override suspend fun updatePizza(
        pizza: Pizza,
        size: SizeInfo?,
        dough: DoughInfo?,
        toppings: List<Topping>?
    ) {
        localDataSource.updatePizza(
            pizza = pizza,
            size = size,
            dough = dough,
            toppings = toppings
        )
    }
}
