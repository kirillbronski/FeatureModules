package com.kbcoding.featuremodules.glue.orders.mappers

import com.kbcoding.featuremodules.formatters.DateTimeFormatter
import com.kbcoding.featuremodules.formatters.PriceFormatter
import com.kbcoding.data.orders.entities.OrderDataEntity
import com.kbcoding.data.orders.entities.RecipientDataEntity
import com.kbcoding.featuremodules.glue.orders.entities.OrderUsdPrice
import com.kbcoding.orders.domain.entities.Order
import com.kbcoding.orders.domain.entities.OrderItem
import javax.inject.Inject

class OrderMapper @Inject constructor(
    private val orderStatusMapper: OrderStatusMapper,
    private val priceFormatter: PriceFormatter,
    private val dateTimeFormatter: DateTimeFormatter,
){

    fun toOrder(orderDataEntity: OrderDataEntity): Order {
        return Order(
            uuid = orderDataEntity.uuid,
            status = orderStatusMapper.toOrderStatus(orderDataEntity.status),
            createdAt = dateTimeFormatter.formatDateTime(orderDataEntity.createdAtMillis),
            orderDeliverInfo = orderDataEntity.recipient.toDeliverInfo(),
            orderItems = orderDataEntity.items.map {
                OrderItem(
                    id = it.id,
                    name = it.productName,
                    quantity = it.quantity,
                    price = OrderUsdPrice(it.priceUsdCents, priceFormatter)
                )
            }
        )
    }

    private fun RecipientDataEntity.toDeliverInfo(): String {
        return "$firstName $lastName, $address"
    }

}