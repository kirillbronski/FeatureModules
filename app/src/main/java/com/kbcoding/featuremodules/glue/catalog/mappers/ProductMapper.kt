package com.kbcoding.featuremodules.glue.catalog.mappers

import com.kbcoding.catalog.domain.entities.Product
import com.kbcoding.featuremodules.formatters.PriceFormatter
import com.kbcoding.featuremodules.glue.catalog.entities.CatalogUsdPrice
import com.kbcoding.data.ProductsDataRepository
import com.kbcoding.data.products.entities.ProductDataEntity
import javax.inject.Inject

class ProductMapper @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
    private val priceFormatter: PriceFormatter,
) {

    suspend fun toProduct(dataEntity: ProductDataEntity): Product {
        val discountPrice = productsDataRepository.getDiscountPriceUsdCentsForEntity(dataEntity)?.let {
            CatalogUsdPrice(it, priceFormatter)
        }
        return Product(
            id = dataEntity.id,
            name = dataEntity.name,
            shortDetails = dataEntity.shortDescription,
            details = dataEntity.description,
            category = dataEntity.category,
            price = CatalogUsdPrice(dataEntity.priceUsdCents, priceFormatter),
            priceWithDiscount = discountPrice,
            photo = dataEntity.imageUrl
        )
    }

}