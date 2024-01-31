package com.kbcoding.catalog.domain.entities

data class ProductWithCartInfo(
    val product: Product,
    val isInCart: Boolean,
)
