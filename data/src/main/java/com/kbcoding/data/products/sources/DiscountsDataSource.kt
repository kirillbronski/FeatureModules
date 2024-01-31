package com.kbcoding.data.products.sources

interface DiscountsDataSource {

    suspend fun getDiscountPercentage(productId: Long): Int?

}