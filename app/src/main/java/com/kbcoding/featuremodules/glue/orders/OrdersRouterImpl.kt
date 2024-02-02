package com.kbcoding.featuremodules.glue.orders

import com.kbcoding.featuremodules.R
import com.kbcoding.navigation.GlobalNavComponentRouter
import com.kbcoding.orders.presentation.OrdersRouter
import javax.inject.Inject

class OrdersRouterImpl @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
): OrdersRouter {

    override fun launchOrdersTab() {
        globalNavComponentRouter.startTabs(startTabDestinationId = R.id.ordersListFragment)
    }

}