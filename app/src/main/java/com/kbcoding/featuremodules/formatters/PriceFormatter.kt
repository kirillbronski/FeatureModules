package com.kbcoding.featuremodules.formatters

interface PriceFormatter {

    /**
     * Convert cents into user-friendly price text.
     */
    fun formatPrice(usdCents: Int): String

}