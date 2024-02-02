package com.kbcoding.featuremodules.glue.orders.di

import com.kbcoding.orders.domain.repositories.CartRepository
import com.kbcoding.orders.domain.repositories.OrdersRepository
import com.kbcoding.orders.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kbcoding.featuremodules.glue.orders.repositories.CartRepositoryImpl
import com.kbcoding.featuremodules.glue.orders.repositories.OrdersRepositoryImpl
import com.kbcoding.featuremodules.glue.orders.repositories.ProductsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface OrderRepositoriesModule {

    @Binds
    fun bindOrdersRepository(
        ordersRepository: OrdersRepositoryImpl
    ): OrdersRepository

    @Binds
    fun bindCartRepository(
        cartRepository: CartRepositoryImpl
    ): CartRepository

    @Binds
    fun bindProductsRepository(
        productsRepository: ProductsRepositoryImpl
    ): ProductsRepository

}