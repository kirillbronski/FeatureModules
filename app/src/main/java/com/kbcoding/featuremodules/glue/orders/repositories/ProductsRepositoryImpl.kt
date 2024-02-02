package com.kbcoding.featuremodules.glue.orders.repositories

import com.kbcoding.data.ProductsDataRepository
import com.kbcoding.orders.domain.repositories.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
) : ProductsRepository {

    override suspend fun changeQuantityBy(productId: Long, byValue: Int) {
        productsDataRepository.changeQuantityBy(productId, byValue)
    }
}