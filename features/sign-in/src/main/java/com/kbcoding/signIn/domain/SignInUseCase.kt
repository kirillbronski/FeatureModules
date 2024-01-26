package com.kbcoding.signIn.domain

import com.kbcoding.signIn.domain.exceptions.EmptyEmailException
import com.kbcoding.signIn.domain.exceptions.EmptyPasswordException
import com.kbcoding.signIn.domain.repositories.AuthServiceRepository
import com.kbcoding.signIn.domain.repositories.AuthTokenRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authTokenRepository: AuthTokenRepository,
    private val authServiceRepository: AuthServiceRepository,
) {

    /**
     * Sign-in to the app by login and password and save auth token.
     * @throws EmptyEmailException if email is blank
     * @throws EmptyPasswordException if password is blank
     */
    suspend fun signIn(email: String, password: String) {
        if (email.isBlank()) throw EmptyEmailException()
        if (password.isBlank()) throw EmptyPasswordException()

        val token = authServiceRepository.signIn(email, password)

        authTokenRepository.setToken(token)
    }

}