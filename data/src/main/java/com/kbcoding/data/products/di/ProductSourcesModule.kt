package com.kbcoding.data.products.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kbcoding.data.products.sources.DiscountsDataSource
import com.kbcoding.data.products.sources.InMemoryDiscountsDataSource
import com.kbcoding.data.products.sources.InMemoryProductsDataSource
import com.kbcoding.data.products.sources.ProductsDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ProductSourcesModule {

    @Binds
    @Singleton
    fun bindProductsSource(
        productsDataSource: InMemoryProductsDataSource
    ): ProductsDataSource

    @Binds
    @Singleton
    fun bindDiscountsSource(
        discountsDataSource: InMemoryDiscountsDataSource
    ): DiscountsDataSource

}