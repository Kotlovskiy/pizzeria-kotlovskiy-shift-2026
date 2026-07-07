package com.example.catalog.data

import com.example.domain.DoughInfo
import com.example.domain.ICatalogRepository
import com.example.domain.Pizza
import com.example.domain.PizzaInfo
import com.example.domain.SizeInfo
import com.example.domain.Topping
import com.example.errors.SmtWentWrongException
import javax.inject.Inject

class CatalogRepository @Inject constructor(
    private val remoteDataSource: CatalogDataSource,
    private val localCatalogDataSource: LocalCatalogDataSource
): ICatalogRepository {
    override suspend fun getPizzas(): List<PizzaInfo> {
        val result = remoteDataSource.getPizzas()
        localCatalogDataSource.savePizzasInfo(result)
        return result
    }

    override suspend fun getPizzaInfoById(id: String): PizzaInfo {
        val localResult = localCatalogDataSource.getPizzaInfoById(id)
        if (localResult != null) return localResult
        getPizzas()
        val secondLocalResult = localCatalogDataSource.getPizzaInfoById(id)
        return secondLocalResult ?: throw SmtWentWrongException()
    }

    override suspend fun addPizza(pizza: Pizza) {
        localCatalogDataSource.addPizza(pizza)
    }

    override suspend fun updatePizza(
        pizza: Pizza,
        size: SizeInfo?,
        dough: DoughInfo?,
        toppings: List<Topping>?
    ) {
        localCatalogDataSource.updatePizza(
            pizza = pizza,
            size = size,
            dough = dough,
            toppings = toppings
        )
    }
}
