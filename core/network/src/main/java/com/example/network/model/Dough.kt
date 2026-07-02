package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Dough {
    @SerialName("thin")
    THIN,

    @SerialName("thick")
    THICK
}
