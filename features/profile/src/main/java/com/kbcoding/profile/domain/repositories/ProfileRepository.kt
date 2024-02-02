package com.kbcoding.profile.domain.repositories

import com.kbcoding.common.Container
import kotlinx.coroutines.flow.Flow
import com.kbcoding.profile.domain.entities.Profile

interface ProfileRepository {

    /**
     * Listen for a profile info of the current logged-in user.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getProfile(): Flow<Container<Profile>>

    /**
     * Reload profile info flow returned by [getProfile]
     */
    fun reload()

    /**
     * Update username of the current logged-in user.
     */
    suspend fun editUsername(newUsername: String)

}