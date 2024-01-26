package com.kbcoding.data.cart.di

import com.kbcoding.data.CartDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.cn.stu.multimodule.data.cart.RealCartDataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CartRepositoriesModule {

    @Binds
    @Singleton
    fun bindCartRepository(
        cartDataRepository: RealCartDataRepository
    ): CartDataRepository

}