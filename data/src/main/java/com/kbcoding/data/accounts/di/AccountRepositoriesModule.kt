package com.kbcoding.data.accounts.di

import com.kbcoding.data.AccountsDataRepository
import com.kbcoding.data.accounts.RealAccountsDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AccountRepositoriesModule {

    @Binds
    @Singleton
    fun bindAccountsRepository(
        accountsDataRepository: RealAccountsDataRepository
    ): AccountsDataRepository

}