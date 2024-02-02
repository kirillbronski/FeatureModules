package com.kbcoding.featuremodules.glue.orders.repositories

import com.kbcoding.common.Container
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.kbcoding.data.OrdersDataRepository
import com.kbcoding.data.orders.entities.CreateOrderDataEntity
import com.kbcoding.data.orders.entities.CreateOrderItemDataEntity
import com.kbcoding.featuremodules.glue.orders.entities.OrderUsdPrice
import com.kbcoding.featuremodules.glue.orders.mappers.OrderMapper
import com.kbcoding.featuremodules.glue.orders.mappers.OrderStatusMapper
import com.kbcoding.featuremodules.glue.orders.mappers.RecipientMapper
import com.kbcoding.orders.domain.entities.Cart
import com.kbcoding.orders.domain.entities.Order
import com.kbcoding.orders.domain.entities.OrderStatus
import com.kbcoding.orders.domain.entities.Recipient
import com.kbcoding.orders.domain.repositories.OrdersRepository
import javax.inject.Inject

class OrdersRepositoryImpl @Inject constructor(
    private val ordersDataRepository: OrdersDataRepository,
    private val orderStatusMapper: OrderStatusMapper,
    private val orderMapper: OrderMapper,
    private val recipientMapper: RecipientMapper,
) : OrdersRepository {

    override suspend fun makeOrder(cart: Cart, recipient: Recipient) {
        ordersDataRepository.createOrder(CreateOrderDataEntity(
            recipientDataEntity = recipientMapper.toRecipientDataEntity(recipient),
            items = cart.cartItems.map {
                CreateOrderItemDataEntity(
                    productId = it.productId,
                    quantity = it.quantity,
                    priceUsdCents = (it.price as OrderUsdPrice).usdCents
                )
            }
        ))
    }

    override suspend fun changeStatus(orderUuid: String, status: OrderStatus) {
        ordersDataRepository.changeStatus(orderUuid, orderStatusMapper.toOrderStatusDataValue(status))
    }

    override fun getOrders(): Flow<Container<List<Order>>> {
        return ordersDataRepository.getOrders().map { container ->
            container.map { list ->
                list.map { orderDataEntity ->
                    orderMapper.toOrder(orderDataEntity)
                }
            }
        }
    }

    override fun reload() {
        ordersDataRepository.reload()
    }
}