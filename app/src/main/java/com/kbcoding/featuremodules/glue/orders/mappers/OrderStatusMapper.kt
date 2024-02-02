package com.kbcoding.featuremodules.glue.orders.mappers

import com.kbcoding.orders.domain.entities.OrderStatus
import com.kbcoding.data.orders.entities.OrderStatusDataValue
import javax.inject.Inject

class OrderStatusMapper @Inject constructor() {

    fun toOrderStatusDataValue(orderStatus: OrderStatus): OrderStatusDataValue {
        return when (orderStatus) {
            OrderStatus.CREATED -> OrderStatusDataValue.CREATED
            OrderStatus.ACCEPTED -> OrderStatusDataValue.ACCEPTED
            OrderStatus.DELIVERING -> OrderStatusDataValue.DELIVERING
            OrderStatus.DONE -> OrderStatusDataValue.DONE
            OrderStatus.CANCELLED -> OrderStatusDataValue.CANCELLED
        }
    }

    fun toOrderStatus(orderStatus: OrderStatusDataValue): OrderStatus {
        return when (orderStatus) {
            OrderStatusDataValue.CREATED -> OrderStatus.CREATED
            OrderStatusDataValue.ACCEPTED -> OrderStatus.ACCEPTED
            OrderStatusDataValue.DELIVERING -> OrderStatus.DELIVERING
            OrderStatusDataValue.DONE -> OrderStatus.DONE
            OrderStatusDataValue.CANCELLED -> OrderStatus.CANCELLED
        }
    }

}