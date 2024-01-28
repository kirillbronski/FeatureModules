package com.kbcoding.signUp.presentation.domain.exceptions

import com.kbcoding.common.AppException

class AccountAlreadyExistsException(
    cause: Throwable? = null
) : AppException(cause = cause)