package com.kbcoding.data.accounts.sources

import com.kbcoding.common.AuthException
import com.kbcoding.data.accounts.entities.AccountDataEntity
import com.kbcoding.data.accounts.entities.SignUpDataEntity
import com.kbcoding.data.accounts.exceptions.AccountAlreadyExistsDataException
import com.kbcoding.data.settings.SettingsDataSource
import kotlinx.coroutines.delay
import java.util.UUID
import javax.inject.Inject

class AccountsDataSourceImpl @Inject constructor(
    private val settings: SettingsDataSource,
) : AccountsDataSource {

    private val records = mutableListOf(
        Record(
            account = AccountDataEntity(
                id = 1,
                email = "admin@google.com",
                username = "admin",
                createdAtMillis = 0
            ),
            password = "123456"
        )
    )

    override suspend fun signIn(email: String, password: String): String {
        delay(1000)
        val record = records.firstOrNull { it.account.email == email && it.password == password }
            ?: throw AuthException()
        UUID.randomUUID().toString().let {
            record.token = it
            return it
        }
    }

    override suspend fun signUp(signUpData: SignUpDataEntity) {
        delay(1000)
        val record = records.firstOrNull { it.account.email == signUpData.email }
        if (record != null) throw AccountAlreadyExistsDataException()
        val newRecord = Record(
            account = AccountDataEntity(
                id = records.size + 1L,
                email = signUpData.email,
                username = signUpData.username,
                createdAtMillis = System.currentTimeMillis(),
            ),
            password = signUpData.password
        )
        records.add(newRecord)
    }

    override suspend fun getAccount(): AccountDataEntity {
        val token = settings.getToken() ?: throw AuthException()
        val record = records.firstOrNull { it.token == token } ?: throw AuthException()
        return record.account
    }

    override suspend fun setAccountUsername(username: String): AccountDataEntity {
        val token = settings.getToken() ?: throw AuthException()
        val record = records.firstOrNull { it.token == token } ?: throw AuthException()
        delay(1000)
        record.account = record.account.copy(
            username = username
        )
        return record.account
    }

    private class Record(
        var account: AccountDataEntity,
        var token: String? = null,
        val password: String,
    )
}