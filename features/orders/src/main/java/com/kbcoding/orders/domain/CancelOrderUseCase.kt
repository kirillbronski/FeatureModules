package com.kbcoding.orders.domain

import com.kbcoding.orders.domain.entities.Order
import com.kbcoding.orders.domain.entities.OrderStatus
import com.kbcoding.orders.domain.exceptions.InvalidOrderStatusException
import com.kbcoding.orders.domain.repositories.OrdersRepository
import javax.inject.Inject

class CancelOrderUseCase @Inject constructor(
    private val ordersRepository: OrdersRepository,
) {

    /**
     * Cancel the specified order.
     * @throws InvalidOrderStatusException
     */
    suspend fun cancelOrder(order: Order) {
        if (!canCancel(order)) {
            throw InvalidOrderStatusException()
        }
        ordersRepository.changeStatus(order.uuid, OrderStatus.CANCELLED)
    }

    /**
     * Whether the order can be cancelled or not.
     */
    fun canCancel(order: Order): Boolean {
        return order.status == OrderStatus.CREATED
                || order.status == OrderStatus.ACCEPTED
    }

}