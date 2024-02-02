package com.kbcoding.data

import com.kbcoding.common.Container
import com.kbcoding.data.orders.entities.CreateOrderDataEntity
import com.kbcoding.data.orders.entities.OrderDataEntity
import com.kbcoding.data.orders.entities.OrderStatusDataValue
import kotlinx.coroutines.flow.Flow

interface OrdersDataRepository {

    /**
     * Listen for all orders.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getOrders(): Flow<Container<List<OrderDataEntity>>>

    /**
     * Reload orders flow returned by [getOrders]
     */
    fun reload()

    /**
     * Change the order status.
     * @throws NotFoundException
     */
    suspend fun changeStatus(orderUuid: String, status: OrderStatusDataValue)

    /**
     * Create a new order.
     * @throws NotFoundException if at least one product ID is unknown
     */
    suspend fun createOrder(data: CreateOrderDataEntity)

}