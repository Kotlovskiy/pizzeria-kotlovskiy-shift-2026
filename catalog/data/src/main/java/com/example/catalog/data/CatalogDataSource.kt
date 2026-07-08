package com.example.catalog.data

import com.example.domain.PizzaInfo
import com.example.errors.NoInternetException
import com.example.errors.toNetworkException
import com.example.network.ApiResult
import com.example.network.api.PizzaApi
import com.example.network.model.PizzasResponse
import com.example.network.toApiResult
import javax.inject.Inject

class CatalogDataSource @Inject constructor(
    private val pizzaApi: PizzaApi,
    private val converter: PizzaResponseConverter
) {
    suspend fun getPizzas(): List<PizzaInfo> {
        return when (val result = pizzaApi.getCatalog().toApiResult()) {
            is ApiResult.Success<PizzasResponse> ->
                converter.convertPizzaResponse(result.data.catalog)
            is ApiResult.HttpError<*> -> throw result.code.toNetworkException()
            is ApiResult.NetworkException -> throw NoInternetException()
        }
    }
}
