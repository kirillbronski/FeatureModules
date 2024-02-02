package com.kbcoding.featuremodules.glue.cart.repositories

import com.kbcoding.data.ProductsDataRepository
import com.kbcoding.features.cart.domain.repositories.ProductsRepository
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
) : ProductsRepository {

    override suspend fun getAvailableQuantity(productId: Long): Int {
        return productsDataRepository.getProductById(productId).quantityAvailable
    }

}