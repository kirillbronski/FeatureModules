package com.kbcoding.data

import com.kbcoding.common.Container
import com.kbcoding.data.products.entities.ProductDataEntity
import com.kbcoding.data.products.entities.ProductDataFilter
import kotlinx.coroutines.flow.Flow

interface ProductsDataRepository {

    /**
     * Listen for products that match the specified filter.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getProducts(filter: ProductDataFilter): Flow<Container<List<ProductDataEntity>>>

    /**
     * Change the quantity by a value for product with the specified ID.
     * @throws NotFoundException
     */
    suspend fun changeQuantityBy(id: Long, quantityBy: Int)

    /**
     * Get product by ID.
     * @throws NotFoundException
     */
    suspend fun getProductById(id: Long): ProductDataEntity

    /**
     * Get min product price.
     */
    suspend fun getMinPriceUsdCents(): Int

    /**
     * Get max product price.
     */
    suspend fun getMaxPriceUsdCents(): Int

    /**
     * Get price with discount for the specified product.
     */
    suspend fun getDiscountPriceUsdCentsForEntity(product: ProductDataEntity): Int?

    /**
     * Get all available categories.
     */
    suspend fun getAllCategories(): List<String>

}