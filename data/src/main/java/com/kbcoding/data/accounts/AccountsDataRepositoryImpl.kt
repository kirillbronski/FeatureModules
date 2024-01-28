package com.kbcoding.data.accounts

import com.kbcoding.common.AuthException
import com.kbcoding.common.Container
import com.kbcoding.common.flow.LazyFlowSubjectFactory
import com.kbcoding.data.AccountsDataRepository
import com.kbcoding.data.accounts.entities.AccountDataEntity
import com.kbcoding.data.accounts.entities.SignUpDataEntity
import com.kbcoding.data.accounts.sources.AccountsDataSource
import com.kbcoding.data.settings.SettingsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountsDataRepositoryImpl @Inject constructor(
    private val accountsDataSource: AccountsDataSource,
    private val settingsDataSource: SettingsDataSource,
    coroutineScope: CoroutineScope,
    lazyFlowSubjectFactory: LazyFlowSubjectFactory,
) : AccountsDataRepository {

    private val accountLazyFlowSubject = lazyFlowSubjectFactory.create {
        accountsDataSource.getAccount()
    }

    init {
        coroutineScope.launch {
            settingsDataSource.listenToken().collect {
                if (it != null) {
                    accountLazyFlowSubject.newAsyncLoad(silently = true)
                } else {
                    accountLazyFlowSubject.updateWith(Container.Error(AuthException()))
                }
            }
        }
    }

    override fun getAccount(): Flow<Container<AccountDataEntity>> {
        return accountLazyFlowSubject.listen()
    }

    override suspend fun setAccountUsername(username: String) {
        val newAccount = accountsDataSource.setAccountUsername(username)
        accountLazyFlowSubject.updateWith(Container.Success(newAccount))
    }

    override suspend fun signIn(email: String, password: String): String {
        return accountsDataSource.signIn(email, password)
    }

    override suspend fun signUp(signUpData: SignUpDataEntity) {
        accountsDataSource.signUp(signUpData)
    }

    override fun reload() {
        accountLazyFlowSubject.newAsyncLoad()
    }

}