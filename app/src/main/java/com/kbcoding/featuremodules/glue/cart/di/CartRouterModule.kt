package com.kbcoding.featuremodules.glue.cart.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ua.cn.stu.multimodule.cart.presentation.CartRouter
import com.kbcoding.featuremodules.glue.cart.CartRouterImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
interface CartRouterModule {

    @Binds
    fun bindCartRouter(cartRouter: CartRouterImpl): CartRouter

}