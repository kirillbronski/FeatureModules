package com.kbcoding.profile.domain

import com.kbcoding.common.Container
import kotlinx.coroutines.flow.Flow
import com.kbcoding.profile.domain.entities.Profile
import com.kbcoding.profile.domain.repositories.ProfileRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    /**
     * Listen for a profile info of the current logged-in user.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getProfile(): Flow<Container<Profile>> {
        return profileRepository.getProfile()
    }

    /**
     * Try to reload profile flow returned by [getProfile].
     */
    fun reload() {
        profileRepository.reload()
    }

}