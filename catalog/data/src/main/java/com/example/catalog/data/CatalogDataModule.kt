package com.example.catalog.data

import com.example.domain.ICatalogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface CatalogDataModule {
    @Binds
    fun bindCatalogRepository(catalogRepository: CatalogRepository): ICatalogRepository
}
