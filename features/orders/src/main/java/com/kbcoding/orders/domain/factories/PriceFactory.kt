package com.kbcoding.orders.domain.factories

import com.kbcoding.orders.domain.entities.Price

interface PriceFactory {

    /**
     * Create a zero price.
     */
    val zero: Price

}