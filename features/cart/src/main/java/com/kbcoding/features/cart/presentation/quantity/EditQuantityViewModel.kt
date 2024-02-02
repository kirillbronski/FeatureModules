package com.kbcoding.features.cart.presentation.quantity

import com.kbcoding.common.Container
import com.kbcoding.features.cart.R
import com.kbcoding.features.cart.domain.GetCartUseCase
import com.kbcoding.features.cart.domain.ValidateCartItemQuantityUseCase
import com.kbcoding.features.cart.domain.entities.CartItem
import com.kbcoding.features.cart.domain.exceptions.QuantityOutOfRangeException
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ua.cn.stu.multimodule.cart.presentation.CartRouter
import com.kbcoding.features.cart.presentation.quantity.EditQuantityDialogFragment.Screen
import com.kbcoding.presentation.BaseViewModel

class EditQuantityViewModel @AssistedInject constructor(
    @Assisted private val screen: Screen,
    private val router: CartRouter,
    private val getCartUseCase: GetCartUseCase,
    private val validateCartItemQuantityUseCase: ValidateCartItemQuantityUseCase,
) : BaseViewModel() {

    val initialQuantityLiveEvent = liveEvent<Int>()
    val stateLiveValue = liveValue<Container<State>>()

    init {
        initialQuantityLiveEvent.publish(screen.initialQuantity)
        load()
    }

    fun load() = debounce {
        loadScreenInto(stateLiveValue) {
            val cartItem = getCartUseCase.getCartById(screen.cartItemId)
            val maxQuantity = validateCartItemQuantityUseCase.getMaxQuantity(cartItem)
            State(cartItem, maxQuantity)
        }
    }

    fun saveNewQuantity(input: String) = debounce {
        val cartItem = stateLiveValue.getValue()?.getOrNull()?.cartItem ?: return@debounce
        val parsedQuantity = try {
            input.toInt()
        } catch (e: Exception) {
            commonUi.toast(resources.getString(R.string.cart_invalid_quantity))
            return@debounce
        }
        viewModelScope.launch {
            try {
                validateCartItemQuantityUseCase.validateNewQuantity(cartItem, parsedQuantity)
                router.sendQuantity(EditQuantityResult(cartItem.id, parsedQuantity))
                router.goBack()
            } catch (e: QuantityOutOfRangeException) {
                commonUi.toast(resources.getString(R.string.cart_quantity_out_of_range))
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(screen: Screen): EditQuantityViewModel
    }

    class State(
        val cartItem: CartItem,
        val maxQuantity: Int
    )

}