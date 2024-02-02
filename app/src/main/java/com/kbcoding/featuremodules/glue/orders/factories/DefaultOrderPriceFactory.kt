package com.kbcoding.featuremodules.glue.orders.factories

import com.kbcoding.featuremodules.formatters.PriceFormatter
import com.kbcoding.featuremodules.glue.orders.entities.OrderUsdPrice
import com.kbcoding.orders.domain.entities.Price
import com.kbcoding.orders.domain.factories.PriceFactory
import javax.inject.Inject

class DefaultOrderPriceFactory @Inject constructor(
    private val priceFormatter: PriceFormatter,
) : PriceFactory {

    override val zero: Price
        get() = OrderUsdPrice(usdCents = 0, formatter = priceFormatter)
}