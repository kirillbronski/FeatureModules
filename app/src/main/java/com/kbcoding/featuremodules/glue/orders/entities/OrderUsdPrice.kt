package com.kbcoding.featuremodules.glue.orders.entities

import com.kbcoding.featuremodules.formatters.PriceFormatter
import com.kbcoding.orders.domain.entities.Price


class OrderUsdPrice(
    val usdCents: Int,
    private val formatter: PriceFormatter,
) : Price {

    override val text: String
        get() = formatter.formatPrice(usdCents)

    override fun plus(price: Price): Price {
        return OrderUsdPrice(
            usdCents = usdCents + (price as OrderUsdPrice).usdCents,
            formatter = formatter
        )
    }
}