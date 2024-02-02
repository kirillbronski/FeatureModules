package com.kbcoding.orders.presentation.orders

import com.kbcoding.common.Container
import com.kbcoding.common.entities.OnChange
import com.kbcoding.orders.domain.CancelOrderUseCase
import com.kbcoding.orders.domain.GetOrdersUseCase
import com.kbcoding.orders.domain.entities.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import com.kbcoding.orders.presentation.orders.entities.UiOrder
import com.kbcoding.presentation.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class OrdersListViewModel @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val cancelOrderUseCase: CancelOrderUseCase,
) : BaseViewModel() {

    private val cancellationUuidsFlow = MutableStateFlow(OnChange(mutableSetOf<String>()))

    val stateLiveValue = combine(
        getOrdersUseCase.getOrders(),
        cancellationUuidsFlow,
        ::merge
    ).toLiveValue()

    fun reload() = debounce {
        getOrdersUseCase.reload()
    }

    fun cancelOrder(order: UiOrder) = debounce {
        viewModelScope.launch {
            val cancellationIds = cancellationUuidsFlow.value.value
            cancellationIds.add(order.uuid)
            cancellationUuidsFlow.value = OnChange(cancellationIds)
            try {
                cancelOrderUseCase.cancelOrder(order.origin)
                cancellationIds.remove(order.uuid) // change cancellation state but do not update
            } catch (e: Exception) {
                cancellationIds.remove(order.uuid) // change cancellation state...
                cancellationUuidsFlow.value = OnChange(cancellationIds) // ... and update
                throw e
            }
        }
    }

    private fun merge(
        ordersContainer: Container<List<Order>>,
        cancellations: OnChange<MutableSet<String>>
    ): Container<State> {
        return ordersContainer.map { list ->
            State(
                orders = list.map {
                    UiOrder(
                        origin = it,
                        canCancel = cancelOrderUseCase.canCancel(it),
                        cancelInProgress = cancellations.value.contains(it.uuid)
                    )
                }
            )
        }
    }

    class State(
        val orders: List<UiOrder>
    )

}