package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PizzaStatus {
    @SerialName("in_processing")
    IN_PROCESSING,

    @SerialName("waiting_courier")
    WAITING_COURIER,

    @SerialName("on_my_way")
    ON_MY_WAY,

    @SerialName("success")
    SUCCESS,

    @SerialName("canceled")
    CANCELED
}
