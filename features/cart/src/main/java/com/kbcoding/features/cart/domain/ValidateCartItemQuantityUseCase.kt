package com.kbcoding.features.cart.domain

import com.kbcoding.features.cart.domain.entities.CartItem
import com.kbcoding.features.cart.domain.exceptions.QuantityOutOfRangeException
import com.kbcoding.features.cart.domain.repositories.ProductsRepository
import javax.inject.Inject

class ValidateCartItemQuantityUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    /**
     * Validate a new quantity for the specified [cartItem].
     * @throws NotFoundException
     * @throws QuantityOutOfRangeException
     */
    suspend fun validateNewQuantity(cartItem: CartItem, newQuantity: Int) {
        if (newQuantity > getMaxQuantity(cartItem)) throw QuantityOutOfRangeException()
        if (newQuantity < 1) throw QuantityOutOfRangeException()
    }

    /**
     * Get max available quantity for the specified [cartItem]
     * @throws NotFoundException
     */
    suspend fun getMaxQuantity(cartItem: CartItem): Int {
        return productsRepository.getAvailableQuantity(cartItem.product.id)
    }

}