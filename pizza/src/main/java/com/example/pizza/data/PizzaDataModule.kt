package com.example.pizza.data

import com.example.pizza.domain.PizzaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface PizzaDataModule {
    @Binds
    fun bindCatalogRepository(pizzaRepository: Repository): PizzaRepository
}
