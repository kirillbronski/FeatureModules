package com.kbcoding.featuremodules.glue.orders.di

import com.kbcoding.orders.presentation.OrdersRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import com.kbcoding.featuremodules.glue.orders.OrdersRouterImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouterModule {

    @Binds
    fun bindRouter(ordersRouter: OrdersRouterImpl): OrdersRouter

}