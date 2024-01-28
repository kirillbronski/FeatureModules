package com.kbcoding.signUp.presentation.domain.exceptions

import com.kbcoding.common.AppException
import com.kbcoding.signUp.presentation.domain.entities.SignUpField

class EmptyFieldException(
    val field: SignUpField,
) : AppException()