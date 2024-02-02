package com.kbcoding.orders.domain

import com.kbcoding.common.Container
import kotlinx.coroutines.flow.Flow
import com.kbcoding.orders.domain.entities.Order
import com.kbcoding.orders.domain.repositories.OrdersRepository
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val ordersRepository: OrdersRepository,
) {

    /**
     * Listen for all user's orders.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getOrders(): Flow<Container<List<Order>>> {
        return ordersRepository.getOrders()
    }

    /**
     * Reload the flow returned by [getOrders].
     */
    fun reload() {
        ordersRepository.reload()
    }

}