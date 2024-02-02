package com.kbcoding.features.cart.domain

import com.kbcoding.features.cart.domain.entities.CartItem
import com.kbcoding.features.cart.domain.repositories.CartRepository
import javax.inject.Inject

class DeleteCartItemUseCase @Inject constructor(
    private val cartRepository: CartRepository,
) {

    /**
     * Delete the list of cart items.
     * @throws NotFoundException
     */
    suspend fun deleteCartItems(cartItems: List<CartItem>) {
        cartRepository.deleteCartItems(cartItems.map { it.id })
    }

}