package com.kbcoding.data.cart.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kbcoding.data.cart.sources.CartDataSource
import com.kbcoding.data.cart.sources.InMemoryCartDataSource
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface CartSourcesModule {

    @Binds
    @Singleton
    fun bindCartSource(cartDataSource: InMemoryCartDataSource): CartDataSource

}