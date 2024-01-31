package com.kbcoding.featuremodules.glue.catalog.entities

import com.kbcoding.catalog.domain.entities.Price
import com.kbcoding.featuremodules.formatters.PriceFormatter

data class CatalogUsdPrice(
    val usdCents: Int,
    private val priceFormatter: PriceFormatter,
) : Price {

    override val text: String
        get() = priceFormatter.formatPrice(usdCents)

    override fun minus(price: Price): Price {
        return CatalogUsdPrice(usdCents - (price as CatalogUsdPrice).usdCents, priceFormatter)
    }

    override fun times(proportion: Double): Price {
        return CatalogUsdPrice((usdCents * proportion).toInt(), priceFormatter)
    }

    override fun compareTo(price: Price): Int {
        return usdCents.compareTo((price as CatalogUsdPrice).usdCents)
    }

    override fun plus(price: Price): Price {
        return CatalogUsdPrice(usdCents + (price as CatalogUsdPrice).usdCents, priceFormatter)
    }

    override fun div(price: Price): Double {
        return usdCents / (price as CatalogUsdPrice).usdCents.toDouble()
    }
}