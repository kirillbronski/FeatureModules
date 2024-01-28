package com.kbcoding.signUp.presentation.domain.repositories

import com.kbcoding.signUp.presentation.domain.entities.SignUpData
import com.kbcoding.signUp.presentation.domain.exceptions.AccountAlreadyExistsException

interface SignUpRepository {

    /**
     * @throws AccountAlreadyExistsException if a user with the provided email already exists
     */
    suspend fun signUp(signUpData: SignUpData)

}