package com.kbcoding.orders.domain.exceptions

import com.kbcoding.common.AppException
import com.kbcoding.orders.domain.entities.Field

class EmptyFieldException(
    val field: Field
) : AppException()