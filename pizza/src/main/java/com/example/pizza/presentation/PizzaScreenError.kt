package com.example.pizza.presentation

interface PizzaScreenError {
    object NoInternet : PizzaScreenError
    object ServerError : PizzaScreenError
    object SmtWentWrong : PizzaScreenError
}
