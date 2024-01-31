package com.kbcoding.featuremodules.glue.catalog.di

import com.kbcoding.catalog.domain.repositories.CartRepository
import com.kbcoding.catalog.domain.repositories.ProductsRepository
import com.kbcoding.featuremodules.glue.catalog.repositories.AdapterCartRepository
import com.kbcoding.featuremodules.glue.catalog.repositories.AdapterProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun provideProductsRepository(
        repository: AdapterProductsRepository
    ): ProductsRepository

    @Binds
    fun provideCartRepository(
        repository: AdapterCartRepository
    ): CartRepository

}