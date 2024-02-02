package com.kbcoding.features.cart.domain.factories

import com.kbcoding.features.cart.domain.entities.Price

interface PriceFactory {

    /**
     * Create a zero price.
     */
    val zero: Price

}