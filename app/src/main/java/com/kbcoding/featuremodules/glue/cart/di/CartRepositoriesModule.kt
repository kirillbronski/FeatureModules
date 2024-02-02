package com.kbcoding.featuremodules.glue.cart.di

import com.kbcoding.features.cart.domain.repositories.CartRepository
import com.kbcoding.features.cart.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kbcoding.featuremodules.glue.cart.repositories.CartRepositoryImpl
import com.kbcoding.featuremodules.glue.cart.repositories.ProductsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface CartRepositoriesModule {

    @Binds
    fun bindCartRepository(cartRepository: CartRepositoryImpl): CartRepository

    @Binds
    fun bindProductRepository(productsRepository: ProductsRepositoryImpl): ProductsRepository

}