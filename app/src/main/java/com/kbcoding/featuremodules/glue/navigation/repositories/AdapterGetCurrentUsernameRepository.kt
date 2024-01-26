package com.kbcoding.featuremodules.glue.navigation.repositories

import com.kbcoding.common.Container
import com.kbcoding.data.AccountsDataRepository
import com.kbcoding.navigation.domain.repositories.GetCurrentUsernameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdapterGetCurrentUsernameRepository @Inject constructor(
    private val accountsRepository: AccountsDataRepository
) : GetCurrentUsernameRepository {

    override fun getCurrentUsername(): Flow<Container<String>> {
        return accountsRepository.getAccount().map { container ->
            container.map { it.username }
        }
    }

    override fun reload() {
        accountsRepository.reload()
    }

}