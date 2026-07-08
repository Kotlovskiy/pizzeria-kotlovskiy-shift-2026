package com.example.network.api

import com.example.network.model.BaseResponse
import com.example.network.model.CancelPizzaOrderDto
import com.example.network.model.PizzaOrderResponse
import com.example.network.model.PizzaOrdersResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface OrderApi {
    @GET("api/pizza/orders")
    suspend fun getAllOrders() : Response<PizzaOrdersResponse>

    @GET("api/pizza/orders/{orderId}")
    suspend fun getOrder(@Path("orderId") orderId: String) : Response<PizzaOrderResponse>

    @PUT("api/pizza/orders/cancel")
    suspend fun cancelOrder(@Body cancelPizzaOrderDto: CancelPizzaOrderDto) : Response<BaseResponse>
}
