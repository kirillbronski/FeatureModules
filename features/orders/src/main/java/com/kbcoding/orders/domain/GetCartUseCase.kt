package com.kbcoding.orders.domain

import com.kbcoding.orders.domain.entities.Cart
import com.kbcoding.orders.domain.repositories.CartRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    /**
     * Get the current user's cart.
     */
    suspend fun getCart(): Cart {
        return cartRepository.getCart()
    }

}