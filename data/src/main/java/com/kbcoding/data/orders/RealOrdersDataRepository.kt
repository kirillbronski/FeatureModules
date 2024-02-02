package com.kbcoding.data.orders

import com.kbcoding.common.Container
import com.kbcoding.common.flow.LazyFlowSubjectFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import com.kbcoding.data.OrdersDataRepository
import com.kbcoding.data.orders.entities.CreateOrderDataEntity
import com.kbcoding.data.orders.entities.OrderDataEntity
import com.kbcoding.data.orders.entities.OrderStatusDataValue
import com.kbcoding.data.orders.sources.OrdersDataSource
import javax.inject.Inject

class RealOrdersDataRepository @Inject constructor(
    private val ordersDataSource: OrdersDataSource,
    lazyFlowSubjectFactory: LazyFlowSubjectFactory,
) : OrdersDataRepository {

    private val ordersSubject = lazyFlowSubjectFactory.create {
        delay(1000)
        ordersDataSource.getOrders()
    }

    override fun getOrders(): Flow<Container<List<OrderDataEntity>>> {
        return ordersSubject.listen()
    }

    override fun reload() {
        ordersSubject.newAsyncLoad()
    }

    override suspend fun changeStatus(orderUuid: String, status: OrderStatusDataValue) {
        ordersDataSource.setStatus(orderUuid, status)
        ordersSubject.newAsyncLoad(silently = true)
    }

    override suspend fun createOrder(data: CreateOrderDataEntity) {
        ordersDataSource.createOrder(data)
        ordersSubject.newAsyncLoad(silently = true)
    }
}