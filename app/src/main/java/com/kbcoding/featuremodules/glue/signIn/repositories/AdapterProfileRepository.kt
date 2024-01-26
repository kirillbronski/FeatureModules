package com.kbcoding.featuremodules.glue.signIn.repositories

import com.kbcoding.common.AuthException
import com.kbcoding.common.unwrapFirst
import com.kbcoding.data.AccountsDataRepository
import com.kbcoding.signIn.domain.repositories.ProfileRepository
import javax.inject.Inject

class AdapterProfileRepository @Inject constructor(
    private val accountsDataRepository: AccountsDataRepository,
) : ProfileRepository {

    override suspend fun canLoadProfile(): Boolean {
        return try {
            accountsDataRepository.getAccount().unwrapFirst()
            true
        } catch (ignored: AuthException) {
            false
        }
    }
}