package com.kbcoding.catalog.domain.repositories

import com.kbcoding.common.Container
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    /**
     * Listen for all product IDs already added to the Cart.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getProductIdentifiersInCart(): Flow<Container<Set<Long>>>

    /**
     * Reload the flow returned by [getProductIdentifiersInCart]
     */
    fun reload()

    /**
     * Add a new product to the cart.
     */
    suspend fun addToCart(productId: Long)

}