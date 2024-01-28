package com.kbcoding.signUp.presentation.domain

import com.kbcoding.signUp.presentation.domain.entities.SignUpData
import com.kbcoding.signUp.presentation.domain.entities.SignUpField
import com.kbcoding.signUp.presentation.domain.exceptions.AccountAlreadyExistsException
import com.kbcoding.signUp.presentation.domain.exceptions.EmptyFieldException
import com.kbcoding.signUp.presentation.domain.exceptions.PasswordMismatchException
import com.kbcoding.signUp.presentation.domain.repositories.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
) {

    /**
     * Create a new account.
     * @throws EmptyFieldException
     * @throws AccountAlreadyExistsException
     * @throws PasswordMismatchException
     */
    suspend fun signUp(signUpData: SignUpData) {
        if (signUpData.email.isBlank()) throw EmptyFieldException(SignUpField.EMAIL)
        if (signUpData.username.isBlank()) throw EmptyFieldException(SignUpField.USERNAME)
        if (signUpData.password.isBlank()) throw EmptyFieldException(SignUpField.PASSWORD)
        if (signUpData.repeatPassword.isBlank()) throw EmptyFieldException(SignUpField.REPEAT_PASSWORD)
        if (signUpData.password != signUpData.repeatPassword) throw PasswordMismatchException()

        signUpRepository.signUp(signUpData)
    }

}