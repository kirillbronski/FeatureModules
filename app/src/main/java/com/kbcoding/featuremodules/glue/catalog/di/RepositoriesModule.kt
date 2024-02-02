package com.kbcoding.featuremodules.glue.catalog.di

import com.kbcoding.catalog.domain.repositories.CartRepository
import com.kbcoding.catalog.domain.repositories.ProductsRepository
import com.kbcoding.featuremodules.glue.catalog.repositories.CartRepositoryImpl
import com.kbcoding.featuremodules.glue.catalog.repositories.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun provideProductsRepository(
        repository: ProductsRepositoryImpl
    ): ProductsRepository

    @Binds
    fun provideCartRepository(
        repository: CartRepositoryImpl
    ): CartRepository

}