package com.kbcoding.orders.domain.repositories

import com.kbcoding.orders.domain.entities.Cart

interface CartRepository {

    suspend fun getCart(): Cart

    /**
     * Remove all items from the cart.
     */
    suspend fun cleanUpCart()

}