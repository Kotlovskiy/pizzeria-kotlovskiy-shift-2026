package com.example.pizza.data

import com.example.keys.AppDataStoreKeys.PIZZAS_INFO_KEY
import com.example.keys.AppDataStoreKeys.PIZZA_KEY
import com.example.pizza.domain.DoughInfo
import com.example.pizza.domain.Pizza
import com.example.pizza.domain.PizzaInfo
import com.example.pizza.domain.SizeInfo
import com.example.pizza.domain.Topping
import com.example.storage.IDataStore
import com.example.storage.PlainDataStore
import kotlinx.serialization.json.Json
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    @param:PlainDataStore private val storage: IDataStore
) {
    suspend fun getPizzaInfoById(id: String): PizzaInfo? {
        val pizzasInfo = storage.getString(PIZZAS_INFO_KEY) ?: return null
        val pizzas = Json.decodeFromString<List<PizzaInfo>>(pizzasInfo)
        pizzas.forEach { info ->
            if (info.id == id) return info
        }
        return null
    }

    suspend fun addPizza(pizza: Pizza) {
        val existingPizzas = getPizzasFromStorage()
        val updatedPizzas = processPizzaAddition(existingPizzas, pizza)
        savePizzasToStorage(updatedPizzas)
    }

    suspend fun updatePizza(
        pizza: Pizza,
        size: SizeInfo? = null,
        dough: DoughInfo? = null,
        toppings: List<Topping>? = null
    ) {
        if (size == null && dough == null && toppings == null) return

        val existingPizzas = getPizzasFromStorage()
        val updatedPizzas = processPizzaUpdate(existingPizzas, pizza, size, dough, toppings)
        savePizzasToStorage(updatedPizzas)
    }

    private suspend fun getPizzasFromStorage(): List<Pizza> {
        return storage.getString(PIZZA_KEY)?.let {
            Json.decodeFromString<List<Pizza>>(it)
        } ?: emptyList()
    }

    private suspend fun savePizzasToStorage(pizzas: List<Pizza>) {
        storage.setString(
            key = PIZZA_KEY,
            value = Json.encodeToString(pizzas)
        )
    }

    private fun processPizzaAddition(
        existingPizzas: List<Pizza>,
        newPizza: Pizza
    ): List<Pizza> {
        return if (existingPizzas.isEmpty()) {
            listOf(newPizza)
        } else {
            addPizzaToExistingList(existingPizzas, newPizza)
        }
    }

    private fun addPizzaToExistingList(
        existingPizzas: List<Pizza>,
        newPizza: Pizza
    ): List<Pizza> {
        val pizzasWithSameId = existingPizzas.filter { it.id == newPizza.id }

        return if (pizzasWithSameId.isEmpty()) {
            existingPizzas + newPizza
        } else {
            handlePizzaWithSameId(existingPizzas, pizzasWithSameId, newPizza)
        }
    }

    private fun handlePizzaWithSameId(
        existingPizzas: List<Pizza>,
        pizzasWithSameId: List<Pizza>,
        newPizza: Pizza
    ): List<Pizza> {
        val matchingPizza = findIdenticalPizza(pizzasWithSameId, newPizza)

        return if (matchingPizza != null) {
            incrementPizzaCount(existingPizzas, matchingPizza)
        } else {
            existingPizzas + newPizza
        }
    }

    private fun findIdenticalPizza(
        pizzas: List<Pizza>,
        targetPizza: Pizza
    ): Pizza? {
        return pizzas.find { existingPizza ->
            existingPizza.size == targetPizza.size &&
                    existingPizza.dough == targetPizza.dough &&
                    existingPizza.toppings.toSet() == targetPizza.toppings.toSet()
        }
    }

    private fun incrementPizzaCount(
        pizzas: List<Pizza>,
        pizzaToIncrement: Pizza,
        increment: Int = 1
    ): List<Pizza> {
        return pizzas.map {
            if (it == pizzaToIncrement) {
                it.copy(count = it.count + increment)
            } else {
                it
            }
        }
    }

    private fun processPizzaUpdate(
        existingPizzas: List<Pizza>,
        pizza: Pizza,
        size: SizeInfo?,
        dough: DoughInfo?,
        toppings: List<Topping>?
    ): List<Pizza> {
        return if (existingPizzas.isEmpty()) {
            existingPizzas
        } else {
            updatePizzaInExistingList(existingPizzas, pizza, size, dough, toppings)
        }
    }

    private fun updatePizzaInExistingList(
        existingPizzas: List<Pizza>,
        pizza: Pizza,
        size: SizeInfo?,
        dough: DoughInfo?,
        toppings: List<Topping>?
    ): List<Pizza> {
        val pizzasWithSameId = existingPizzas.filter { it.id == pizza.id }

        return if (pizzasWithSameId.isEmpty()) {
            existingPizzas
        } else {
            handlePizzaUpdate(existingPizzas, pizzasWithSameId, pizza, size, dough, toppings)
        }
    }

    private fun handlePizzaUpdate(
        existingPizzas: List<Pizza>,
        pizzasWithSameId: List<Pizza>,
        pizza: Pizza,
        size: SizeInfo?,
        dough: DoughInfo?,
        toppings: List<Topping>?
    ): List<Pizza> {
        val matchingPizza = findIdenticalPizza(pizzasWithSameId, pizza) ?: return existingPizzas

        val targetPizza = findPizzaWithUpdatedParams(pizzasWithSameId, pizza, size, dough, toppings)

        return if (targetPizza != null) {
            incrementPizzaCount(existingPizzas, targetPizza, pizza.count)
                .filter { it != matchingPizza }
        } else {
            updatePizzaParams(existingPizzas, matchingPizza, size, dough, toppings)
        }
    }

    private fun findPizzaWithUpdatedParams(
        pizzasWithSameId: List<Pizza>,
        pizza: Pizza,
        size: SizeInfo?,
        dough: DoughInfo?,
        toppings: List<Topping>?
    ): Pizza? {
        return pizzasWithSameId.find { existingPizza ->
            existingPizza.size == (size ?: pizza.size) &&
                    existingPizza.dough == (dough ?: pizza.dough) &&
                    existingPizza.toppings.toSet() == (toppings?.toSet() ?: pizza.toppings.toSet())
        }
    }

    private fun updatePizzaParams(
        pizzas: List<Pizza>,
        pizzaToUpdate: Pizza,
        size: SizeInfo?,
        dough: DoughInfo?,
        toppings: List<Topping>?
    ): List<Pizza> {
        return pizzas.map {
            if (it == pizzaToUpdate) {
                it.copy(
                    size = size ?: it.size,
                    dough = dough ?: it.dough,
                    toppings = toppings ?: it.toppings
                )
            } else {
                it
            }
        }
    }
}
