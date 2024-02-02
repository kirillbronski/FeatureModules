package com.kbcoding.data.orders.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kbcoding.data.orders.sources.InMemoryOrdersDataSource
import com.kbcoding.data.orders.sources.OrdersDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface OrdersSourcesModule {

    @Binds
    @Singleton
    fun bindOrdersDataSource(
        ordersDataSource: InMemoryOrdersDataSource,
    ): OrdersDataSource

}