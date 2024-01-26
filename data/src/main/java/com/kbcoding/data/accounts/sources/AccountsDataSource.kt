package com.kbcoding.data.accounts.sources

import com.kbcoding.data.accounts.entities.AccountDataEntity
import com.kbcoding.data.accounts.entities.SignUpDataEntity

interface AccountsDataSource {

    suspend fun signIn(email: String, password: String): String

    suspend fun signUp(signUpData: SignUpDataEntity)

    suspend fun getAccount(): AccountDataEntity

    suspend fun setAccountUsername(username: String): AccountDataEntity

}