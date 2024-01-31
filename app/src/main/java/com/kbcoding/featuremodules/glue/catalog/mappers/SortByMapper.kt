package com.kbcoding.featuremodules.glue.catalog.mappers

import com.kbcoding.catalog.domain.entities.SortBy
import com.kbcoding.data.products.entities.SortByDataValue
import javax.inject.Inject

class SortByMapper @Inject constructor() {

    fun toSortByDataValue(sortBy: SortBy): SortByDataValue {
        return when (sortBy) {
            SortBy.PRICE -> SortByDataValue.PRICE
            SortBy.NAME -> SortByDataValue.NAME
        }
    }

}