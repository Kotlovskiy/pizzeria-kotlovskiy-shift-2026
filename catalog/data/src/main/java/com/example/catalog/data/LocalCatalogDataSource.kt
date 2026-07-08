package com.example.catalog.data

import com.example.domain.PizzaInfo
import com.example.keys.AppDataStoreKeys.PIZZAS_INFO_KEY
import com.example.storage.IDataStore
import com.example.storage.PlainDataStore
import kotlinx.serialization.json.Json
import javax.inject.Inject

class LocalCatalogDataSource @Inject constructor(
    @param:PlainDataStore private val storage: IDataStore
) {
    suspend fun savePizzasInfo(pizzasInfo: List<PizzaInfo>) {
        storage.setString(
            key = PIZZAS_INFO_KEY,
            value = Json.encodeToString(pizzasInfo)
        )
    }
}
