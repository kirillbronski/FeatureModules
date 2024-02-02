package com.kbcoding.featuremodules.glue.orders.repositories

import com.kbcoding.common.unwrapFirst
import com.kbcoding.data.CartDataRepository
import com.kbcoding.data.ProductsDataRepository
import com.kbcoding.featuremodules.formatters.PriceFormatter
import com.kbcoding.featuremodules.glue.orders.entities.OrderUsdPrice
import com.kbcoding.orders.domain.entities.Cart
import com.kbcoding.orders.domain.entities.CartItem
import com.kbcoding.orders.domain.factories.PriceFactory
import com.kbcoding.orders.domain.repositories.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDataRepository: CartDataRepository,
    private val productsDataRepository: ProductsDataRepository,
    private val priceFormatter: PriceFormatter,
    private val priceFactory: PriceFactory,
) : CartRepository {

    override suspend fun cleanUpCart() {
        cartDataRepository.deleteAll()
    }

    override suspend fun getCart(): Cart {
        val cartItems = cartDataRepository.getCart().unwrapFirst()
        return Cart(
            cartItems = cartItems.map {
                val product = productsDataRepository.getProductById(it.productId)
                val priceWithDiscount = productsDataRepository.getDiscountPriceUsdCentsForEntity(product)
                    ?: product.priceUsdCents
                CartItem(
                    name = product.name,
                    productId = it.productId,
                    price = OrderUsdPrice(priceWithDiscount, priceFormatter),
                    quantity = it.quantity,
                )
            },
            priceFactory = priceFactory,
        )
    }
}