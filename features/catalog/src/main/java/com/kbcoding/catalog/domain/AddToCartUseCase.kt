package com.kbcoding.catalog.domain

import com.kbcoding.catalog.domain.entities.Product
import com.kbcoding.catalog.domain.repositories.CartRepository
import com.kbcoding.common.Container
import com.kbcoding.common.Core
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
) {

    /**
     * Add a new product to the Cart.
     */
    suspend fun addToCart(product: Product) = withTimeout(Core.localTimeoutMillis) {
        val productIdsInCart = cartRepository.getProductIdentifiersInCart()
            .filterIsInstance<Container.Success<Set<Long>>>()
            .first()
        if (!productIdsInCart.value.contains(product.id)) {
            cartRepository.addToCart(product.id)
        }
    }
}