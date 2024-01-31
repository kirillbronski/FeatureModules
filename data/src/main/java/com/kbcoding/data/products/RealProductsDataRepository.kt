package com.kbcoding.data.products

import com.kbcoding.common.Container
import com.kbcoding.common.entities.OnChange
import com.kbcoding.common.flow.LazyFlowSubjectFactory
import com.kbcoding.data.ProductsDataRepository
import com.kbcoding.data.products.entities.ProductDataEntity
import com.kbcoding.data.products.entities.ProductDataFilter
import com.kbcoding.data.products.sources.ProductsDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class RealProductsDataRepository @Inject constructor(
    private val productsDataSource: ProductsDataSource,
    private val lazyFlowSubjectFactory: LazyFlowSubjectFactory,
) : ProductsDataRepository {

    private val updateNotifierFlow = MutableStateFlow(OnChange(Unit))

    override suspend fun getProductById(id: Long): ProductDataEntity {
        return productsDataSource.getProductById(id)
    }

    @ExperimentalCoroutinesApi
    override fun getProducts(filter: ProductDataFilter): Flow<Container<List<ProductDataEntity>>> {
        return updateNotifierFlow.flatMapLatest {
            lazyFlowSubjectFactory.create {
                delay(1000)
                productsDataSource.getProducts(filter)
            }.listen()
        }
    }

    override suspend fun changeQuantityBy(id: Long, quantityBy: Int) {
        productsDataSource.changeQuantityBy(id, quantityBy)
        updateNotifierFlow.value = OnChange(Unit)
    }

    override suspend fun getMinPriceUsdCents(): Int {
        return productsDataSource.getProducts(ProductDataFilter.DEFAULT)
            .minOf { getDiscountPriceUsdCentsForEntity(it) ?: it.priceUsdCents }
    }

    override suspend fun getMaxPriceUsdCents(): Int {
        return productsDataSource.getProducts(ProductDataFilter.DEFAULT)
            .maxOf { getDiscountPriceUsdCentsForEntity(it) ?: it.priceUsdCents }
    }

    override suspend fun getDiscountPriceUsdCentsForEntity(product: ProductDataEntity): Int? {
        return productsDataSource.getDiscountPriceUsdCentsForEntity(product)
    }

    override suspend fun getAllCategories(): List<String> {
        return productsDataSource.getAllCategories()
    }

}