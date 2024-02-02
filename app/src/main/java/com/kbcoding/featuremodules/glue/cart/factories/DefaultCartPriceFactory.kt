package com.kbcoding.featuremodules.glue.cart.factories

import com.kbcoding.featuremodules.formatters.PriceFormatter
import com.kbcoding.featuremodules.glue.cart.entities.CartUsdPrice
import com.kbcoding.features.cart.domain.entities.Price
import com.kbcoding.features.cart.domain.factories.PriceFactory
import javax.inject.Inject

class DefaultCartPriceFactory @Inject constructor(
    private val priceFormatter: PriceFormatter
): PriceFactory {

    override val zero: Price
        get() = CartUsdPrice(0, priceFormatter)

}