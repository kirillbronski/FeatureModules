package com.kbcoding.data.products.di

import com.kbcoding.data.ProductsDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kbcoding.data.products.RealProductsDataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ProductRepositoriesModule {

    @Singleton
    @Binds
    fun bindProductRepository(
        productsDataRepository: RealProductsDataRepository
    ): ProductsDataRepository
}