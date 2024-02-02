package com.kbcoding.featuremodules.glue.cart.di

import com.kbcoding.features.cart.domain.factories.PriceFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kbcoding.featuremodules.glue.cart.factories.DefaultCartPriceFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CartFactoriesModule {

    @Binds
    @Singleton
    fun bindPriceFactory(priceFactory: DefaultCartPriceFactory): PriceFactory

}