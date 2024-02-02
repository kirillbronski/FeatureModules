package com.kbcoding.data.orders.sources

import com.kbcoding.data.orders.entities.CreateOrderDataEntity
import com.kbcoding.data.orders.entities.OrderDataEntity
import com.kbcoding.data.orders.entities.OrderStatusDataValue

interface OrdersDataSource {

    suspend fun getOrders(): List<OrderDataEntity>

    suspend fun setStatus(uuid: String, newStatus: OrderStatusDataValue)

    suspend fun createOrder(createOrderDataEntity: CreateOrderDataEntity)

}