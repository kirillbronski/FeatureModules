package com.kbcoding.signIn.domain.repositories

interface ProfileRepository {

    /**
     * Whether it's possible to load a profile of the current logged-in
     * user or not.
     */
    suspend fun canLoadProfile(): Boolean

}