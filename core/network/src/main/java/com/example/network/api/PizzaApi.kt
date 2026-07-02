package com.example.network.api

import com.example.network.model.CreatePizzaPaymentDto
import com.example.network.model.PizzaPaymentResponse
import com.example.network.model.PizzasResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PizzaApi {
    @GET("api/pizza/catalog")
    suspend fun getCatalog(): Response<PizzasResponse>

    @POST("api/pizza/payment")
    suspend fun payCart(@Body createPizzaPaymentDto: CreatePizzaPaymentDto): Response<PizzaPaymentResponse>
}
