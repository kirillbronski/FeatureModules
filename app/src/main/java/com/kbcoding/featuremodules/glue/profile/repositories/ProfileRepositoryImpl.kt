package com.kbcoding.featuremodules.glue.profile.repositories

import com.kbcoding.common.Container
import com.kbcoding.data.AccountsDataRepository
import com.kbcoding.featuremodules.formatters.DateTimeFormatter
import com.kbcoding.profile.domain.entities.Profile
import com.kbcoding.profile.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val accountsDataRepository: AccountsDataRepository,
    private val dateTimeFormatter: DateTimeFormatter,
) : ProfileRepository {

    override fun getProfile(): Flow<Container<Profile>> {
        return accountsDataRepository.getAccount().map { container ->
            container.map {
                Profile(
                    id = it.id,
                    username = it.username,
                    email = it.email,
                    createdAtString = dateTimeFormatter.formatDateTime(it.createdAtMillis)
                )
            }
        }
    }

    override fun reload() {
        accountsDataRepository.reload()
    }

    override suspend fun editUsername(newUsername: String) {
        accountsDataRepository.setAccountUsername(newUsername)
    }

}