package com.kbcoding.featuremodules.glue.signIn.repositories

import com.kbcoding.data.AccountsDataRepository
import com.kbcoding.signIn.domain.repositories.AuthServiceRepository
import javax.inject.Inject

class AdapterAuthServiceImpl @Inject constructor(
    private val accountsDataRepository: AccountsDataRepository,
) : AuthServiceRepository {

    override suspend fun signIn(email: String, password: String): String {
        return accountsDataRepository.signIn(email, password)
    }

}