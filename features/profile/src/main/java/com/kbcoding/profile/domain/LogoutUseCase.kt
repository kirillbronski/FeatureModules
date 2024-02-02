package com.kbcoding.profile.domain

import com.kbcoding.profile.domain.repositories.AuthTokenRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authTokenRepository: AuthTokenRepository,
) {

    /**
     * Logout from the app.
     */
    suspend fun logout() {
        authTokenRepository.clearToken()
    }

}