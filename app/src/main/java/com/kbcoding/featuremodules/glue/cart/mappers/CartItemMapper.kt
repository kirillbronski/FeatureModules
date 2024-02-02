package com.kbcoding.featuremodules.glue.cart.mappers

import com.kbcoding.data.ProductsDataRepository
import com.kbcoding.data.cart.entities.CartItemDataEntity
import com.kbcoding.featuremodules.formatters.PriceFormatter
import com.kbcoding.featuremodules.glue.cart.entities.CartUsdPrice
import com.kbcoding.features.cart.domain.entities.CartItem
import com.kbcoding.features.cart.domain.entities.Product
import javax.inject.Inject

class CartItemMapper @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
    private val priceFormatter: PriceFormatter,
) {

    suspend fun toCartItem(dataEntity: CartItemDataEntity): CartItem {
        val productDataEntity = productsDataRepository.getProductById(dataEntity.id)
        val productPriceWithDiscount = productsDataRepository.getDiscountPriceUsdCentsForEntity(productDataEntity)
        val product = Product(
            id = productDataEntity.id,
            name = productDataEntity.name,
            shortDetails = productDataEntity.shortDescription,
            photo = productDataEntity.imageUrl,
            totalQuantity = productDataEntity.quantityAvailable
        )
        val discountPerItem = productDataEntity.priceUsdCents - (productPriceWithDiscount ?: productDataEntity.priceUsdCents)
        return CartItem(
            id = dataEntity.id,
            product = product,
            quantity = dataEntity.quantity,
            pricePerItem = CartUsdPrice(productDataEntity.priceUsdCents, priceFormatter),
            discountPerItem = CartUsdPrice(discountPerItem, priceFormatter)
        )
    }
}