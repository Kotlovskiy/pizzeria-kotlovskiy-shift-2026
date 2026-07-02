package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Ingredient {
    @SerialName("pineapple")
    PINEAPPLE,

    @SerialName("mozzarella")
    MOZZARELLA,

    @SerialName("pepperoni")
    PEPPERONI,

    @SerialName("green_pepper")
    GREEN_PEPPER,

    @SerialName("mushrooms")
    MUSHROOMS,

    @SerialName("basil")
    BASIL,

    @SerialName("cheddar")
    CHEDDAR,

    @SerialName("parmesan")
    PARMESAN,

    @SerialName("feta")
    FETA,

    @SerialName("ham")
    HAM,

    @SerialName("pickle")
    PICKLE,

    @SerialName("tomato")
    TOMATO,

    @SerialName("bacon")
    BACON,

    @SerialName("onion")
    ONION,

    @SerialName("chile")
    CHILE,

    @SerialName("shrimp")
    SHRIMP,

    @SerialName("chicken_fillet")
    CHICKEN_FILLET,

    @SerialName("meatballs")
    MEATBALLS
}
