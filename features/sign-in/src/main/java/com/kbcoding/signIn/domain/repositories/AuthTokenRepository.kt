package com.kbcoding.signIn.domain.repositories

interface AuthTokenRepository {

    /**
     * Save the auth token.
     */
    suspend fun setToken(token: String?)

    /**
     * Get the auth token of the current logged-in user.
     */
    suspend fun getToken(): String?

}