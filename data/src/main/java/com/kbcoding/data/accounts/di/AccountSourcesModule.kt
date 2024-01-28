package com.kbcoding.data.accounts.di

import com.kbcoding.data.accounts.sources.AccountsDataSource
import com.kbcoding.data.accounts.sources.AccountsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AccountSourcesModule {

    @Binds
    @Singleton
    fun bindAccountSource(
        accountsDataSource: AccountsDataSourceImpl
    ): AccountsDataSource

}