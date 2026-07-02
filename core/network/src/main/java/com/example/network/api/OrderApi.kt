package com.example.network.api

import com.example.network.model.BaseResponse
import com.example.network.model.CancelPizzaOrderDto
import com.example.network.model.PizzaOrderResponse
import com.example.network.model.PizzaOrdersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT

interface OrderApi {
    @GET
    fun getAllOrders() : Response<PizzaOrdersResponse>

    @GET
    fun getOrder(orderId: String) : Response<PizzaOrderResponse>

    @PUT
    fun cancelOrder(cancelPizzaOrderDto: CancelPizzaOrderDto) : Response<BaseResponse>
}
