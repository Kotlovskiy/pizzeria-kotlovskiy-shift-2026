package com.example.network.api

import com.example.network.model.PizzasResponse
import retrofit2.Response
import retrofit2.http.GET

interface PizzaApi {
    @GET("api/pizza/catalog")
    suspend fun getCatalog(): Response<PizzasResponse>

}
