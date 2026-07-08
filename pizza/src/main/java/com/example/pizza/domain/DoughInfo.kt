package com.example.pizza.domain

import kotlinx.serialization.Serializable

@Serializable
data class DoughInfo(
    val type: Dough,
    val price: Int
)
