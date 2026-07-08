package com.example.catalog.data

import com.example.domain.ICatalogRepository
import com.example.domain.PizzaInfo
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
}
