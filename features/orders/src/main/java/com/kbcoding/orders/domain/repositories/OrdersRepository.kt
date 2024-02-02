package com.kbcoding.orders.domain.repositories

import com.kbcoding.common.Container
import com.kbcoding.orders.domain.entities.Cart
import com.kbcoding.orders.domain.entities.Order
import com.kbcoding.orders.domain.entities.OrderStatus
import com.kbcoding.orders.domain.entities.Recipient
import kotlinx.coroutines.flow.Flow

interface OrdersRepository {

    /**
     * Create a new order by using items in the cart.
     */
    suspend fun makeOrder(cart: Cart, recipient: Recipient)

    /**
     * Change status of the specified order.
     */
    suspend fun changeStatus(orderUuid: String, status: OrderStatus)

    /**
     * Listen for order list.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getOrders(): Flow<Container<List<Order>>>

    /**
     * Reload orders flow returned by [getOrders]
     */
    fun reload()
    
}