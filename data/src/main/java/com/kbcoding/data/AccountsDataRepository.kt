package com.kbcoding.data

import com.kbcoding.common.Container
import com.kbcoding.data.accounts.entities.AccountDataEntity
import com.kbcoding.data.accounts.entities.SignUpDataEntity
import kotlinx.coroutines.flow.Flow


interface AccountsDataRepository {

    /**
     * Listen for the current account.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getAccount(): Flow<Container<AccountDataEntity>>

    /**
     * Update the username of the current logged-in user.
     */
    suspend fun setAccountUsername(username: String)

    /**
     * Exchange email-password to auth token.
     */
    suspend fun signIn(email: String, password: String): String

    /**
     * Create a new account.
     * @throws AccountAlreadyExistsDataException
     */
    suspend fun signUp(signUpData: SignUpDataEntity)

    /**
     * Reload the flow returned by [getAccount]
     */
    fun reload()

}