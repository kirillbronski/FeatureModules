package com.kbcoding.features.cart.domain

import com.kbcoding.common.Container
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.kbcoding.features.cart.domain.entities.Cart
import com.kbcoding.features.cart.domain.entities.CartItem
import com.kbcoding.features.cart.domain.repositories.CartRepository
import com.kbcoding.features.cart.domain.factories.PriceFactory
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val priceFactory: PriceFactory,
) {

    /**
     * Listen for user's Cart.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getCart(): Flow<Container<Cart>> {
        return cartRepository.getCartItems().map { container ->
            container.map {
                Cart(it, priceFactory)
            }
        }
    }

    /**
     * Get cart item by ID.
     * @throws NotFoundException
     */
    suspend fun getCartById(cartItemId: Long): CartItem {
        return cartRepository.getCartItemById(cartItemId)
    }

    /**
     * Reload Cart flow returned by [getCart].
     */
    fun reload() {
        cartRepository.reload()
    }

}