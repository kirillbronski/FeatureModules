package com.kbcoding.featuremodules.glue.catalog.repositories

import com.kbcoding.catalog.domain.entities.Price
import com.kbcoding.catalog.domain.entities.Product
import com.kbcoding.catalog.domain.entities.ProductFilter
import com.kbcoding.catalog.domain.repositories.ProductsRepository
import com.kbcoding.common.Container
import com.kbcoding.featuremodules.formatters.PriceFormatter
import com.kbcoding.featuremodules.glue.catalog.entities.CatalogUsdPrice
import com.kbcoding.featuremodules.glue.catalog.mappers.ProductFilterMapper
import com.kbcoding.featuremodules.glue.catalog.mappers.ProductMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.kbcoding.data.ProductsDataRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
    private val productMapper: ProductMapper,
    private val productFilterMapper: ProductFilterMapper,
    private val priceFormatter: PriceFormatter,
) : ProductsRepository {

    override fun getProducts(filter: ProductFilter): Flow<Container<List<Product>>> {
        val dataFilter = productFilterMapper.toProductDataFilter(filter)
        return productsDataRepository.getProducts(dataFilter).map { container ->
            container.suspendMap { list ->
                list.map { dataEntity ->
                    productMapper.toProduct(dataEntity)
                }
            }
        }
    }

    override suspend fun getProduct(id: Long): Product {
        val productDataEntity = productsDataRepository.getProductById(id)
        return productMapper.toProduct(productDataEntity)
    }

    override suspend fun getMinPossiblePrice(): Price {
        return CatalogUsdPrice(productsDataRepository.getMinPriceUsdCents(), priceFormatter)
    }

    override suspend fun getMaxPossiblePrice(): Price {
        return CatalogUsdPrice(productsDataRepository.getMaxPriceUsdCents(), priceFormatter)
    }

    override suspend fun getAllCategories(): List<String> {
        return productsDataRepository.getAllCategories()
    }

}