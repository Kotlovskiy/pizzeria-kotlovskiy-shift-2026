package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Size {
    @SerialName("small")
    SMALL,

    @SerialName("medium")
    MEDIUM,

    @SerialName("large")
    LARGE
}
