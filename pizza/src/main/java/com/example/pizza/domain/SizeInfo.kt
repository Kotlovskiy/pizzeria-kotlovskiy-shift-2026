package com.example.pizza.domain

import kotlinx.serialization.Serializable

@Serializable
data class SizeInfo(
    val size: Size,
    val price: Int
)
