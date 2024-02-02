package com.kbcoding.features.cart.domain.repositories

interface ProductsRepository {

    /**
     * @throws NotFoundException
     */
    suspend fun getAvailableQuantity(productId: Long): Int

}