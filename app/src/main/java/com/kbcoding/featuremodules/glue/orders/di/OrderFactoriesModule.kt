package com.kbcoding.featuremodules.glue.orders.di

import com.kbcoding.orders.domain.factories.PriceFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kbcoding.featuremodules.glue.orders.factories.DefaultOrderPriceFactory

@Module
@InstallIn(SingletonComponent::class)
interface OrderFactoriesModule {

    @Binds
    fun bindOrderPriceFactory(
        priceFactory: DefaultOrderPriceFactory
    ): PriceFactory

}