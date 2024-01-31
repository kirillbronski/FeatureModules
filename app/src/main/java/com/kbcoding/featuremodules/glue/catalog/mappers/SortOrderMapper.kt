package com.kbcoding.featuremodules.glue.catalog.mappers

import com.kbcoding.catalog.domain.entities.SortOrder
import com.kbcoding.data.products.entities.SortOrderDataValue
import javax.inject.Inject

class SortOrderMapper @Inject constructor() {

    fun toSortOrderDataValue(sortOrder: SortOrder): SortOrderDataValue {
        return when (sortOrder) {
            SortOrder.DESC -> SortOrderDataValue.DESC
            SortOrder.ASC -> SortOrderDataValue.ASC
        }
    }

}