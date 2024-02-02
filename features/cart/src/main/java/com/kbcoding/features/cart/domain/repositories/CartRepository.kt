package com.kbcoding.features.cart.domain.repositories

import com.kbcoding.common.Container
import com.kbcoding.features.cart.domain.entities.CartItem
import kotlinx.coroutines.flow.Flow


interface CartRepository {

    /**
     * Change the quantity of [cartItemId] to the [newQuantity] value.
     * @throws NotFoundException
     */
    suspend fun changeQuantity(cartItemId: Long, newQuantity: Int)

    /**
     * Delete the specified cart items.
     * @throws NotFoundException
     */
    suspend fun deleteCartItems(cartItemIds: List<Long>)

    /**
     * Get cart item by ID.
     * @throws NotFoundException
     */
    suspend fun getCartItemById(id: Long): CartItem

    /**
     * Listen for user's Cart.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getCartItems(): Flow<Container<List<CartItem>>>

    /**
     * Reload Cart flow returned by [getCartItems].
     */
    fun reload()

}