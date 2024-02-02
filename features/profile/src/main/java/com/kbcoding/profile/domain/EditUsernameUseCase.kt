package com.kbcoding.profile.domain

import com.kbcoding.profile.domain.exceptions.EmptyUsernameException
import com.kbcoding.profile.domain.repositories.ProfileRepository
import javax.inject.Inject

class EditUsernameUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    /**
     * Update a username of the current logged-in user.
     * @throws EmptyUsernameException
     */
    suspend fun editUsername(newUsername: String) {
        if (newUsername.isBlank()) throw EmptyUsernameException()
        profileRepository.editUsername(newUsername)
    }

}