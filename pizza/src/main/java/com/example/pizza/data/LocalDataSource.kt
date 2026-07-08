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
        val existingPizzas = storage.getString(PIZZA_KEY)?.let {
            Json.decodeFromString<List<Pizza>>(it)
        } ?: emptyList()

        val updatedPizzas = if (existingPizzas.isEmpty()) {
            listOf(pizza)
        } else {
            val sameIdPizzas = existingPizzas.filter { it.id == pizza.id }

            if (sameIdPizzas.isEmpty()) {
                existingPizzas + pizza
            } else {
                val matchingPizza = sameIdPizzas.find { existingPizza ->
                    existingPizza.size == pizza.size &&
                            existingPizza.dough == pizza.dough &&
                            existingPizza.toppings.toSet() == pizza.toppings.toSet()
                }

                if (matchingPizza != null) {
                    existingPizzas.map {
                        if (it == matchingPizza) {
                            it.copy(count = it.count + 1)
                        } else {
                            it
                        }
                    }
                } else {
                    existingPizzas + pizza
                }
            }
        }

        storage.setString(
            key = PIZZA_KEY,
            value = Json.encodeToString(updatedPizzas)
        )
    }

    suspend fun updatePizza(
        pizza: Pizza,
        size: SizeInfo? = null,
        dough: DoughInfo? = null,
        toppings: List<Topping>? = null
    ) {
        if (size == null && dough == null && toppings == null) return

        val existingPizzas = storage.getString(PIZZA_KEY)?.let {
            Json.decodeFromString<List<Pizza>>(it)
        } ?: emptyList()

        val updatedPizzas = if (existingPizzas.isEmpty()) {
            listOf(pizza)
        } else {
            val sameIdPizzas = existingPizzas.filter { it.id == pizza.id }

            if (sameIdPizzas.isEmpty()) {
                existingPizzas + pizza
            } else {
                val matchingPizza = sameIdPizzas.find { existingPizza ->
                    existingPizza.size == pizza.size &&
                            existingPizza.dough == pizza.dough &&
                            existingPizza.toppings.toSet() == pizza.toppings.toSet()
                }

                val resultPizza = sameIdPizzas.find { existingPizza ->
                    existingPizza.size == (size ?: pizza.size) &&
                            existingPizza.dough == (dough ?: pizza.dough) &&
                            existingPizza.toppings.toSet() == (toppings?.toSet() ?: pizza.toppings.toSet())
                }

                if (matchingPizza != null) {
                    if(resultPizza != null) {
                        existingPizzas
                            .filter { it != matchingPizza }
                            .map {
                                if (it == resultPizza) {
                                    it.copy(
                                        count = it.count + pizza.count
                                    )
                                } else {
                                    it
                                }
                            }
                    } else {
                        existingPizzas.map {
                            if (it == matchingPizza) {
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
                } else {
                    existingPizzas + pizza
                }
            }
        }

        storage.setString(
            key = PIZZA_KEY,
            value = Json.encodeToString(updatedPizzas)
        )
    }
}
