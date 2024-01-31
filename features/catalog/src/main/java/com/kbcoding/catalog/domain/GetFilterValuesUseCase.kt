package com.kbcoding.catalog.domain

import com.kbcoding.catalog.domain.entities.Price
import com.kbcoding.catalog.domain.repositories.ProductsRepository
import javax.inject.Inject

class GetFilterValuesUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
) {

    /**
     * Get min possible product price.
     */
    suspend fun getMinPrice(): Price {
        return productsRepository.getMinPossiblePrice()
    }

    /**
     * Get max possible product price.
     */
    suspend fun getMaxPrice(): Price {
        return productsRepository.getMaxPossiblePrice()
    }

    /**
     * Get all available product categories.
     */
    suspend fun getCategories(): List<String> {
        return productsRepository.getAllCategories()
    }

}