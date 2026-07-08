package com.example.network.api

import com.example.network.model.CreatePizzaPaymentDto
import com.example.network.model.PizzaPaymentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentApi {
    @POST("api/pizza/payment")
    suspend fun payCart(@Body createPizzaPaymentDto: CreatePizzaPaymentDto): Response<PizzaPaymentResponse>
}
