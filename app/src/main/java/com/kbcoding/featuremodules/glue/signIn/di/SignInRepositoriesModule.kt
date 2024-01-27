package com.kbcoding.featuremodules.glue.signIn.di

import com.kbcoding.featuremodules.glue.signIn.repositories.AdapterAuthServiceImpl
import com.kbcoding.featuremodules.glue.signIn.repositories.AuthTokenRepositoryImpl
import com.kbcoding.featuremodules.glue.signIn.repositories.ProfileRepositoryImpl
import com.kbcoding.signIn.domain.repositories.AuthServiceRepository
import com.kbcoding.signIn.domain.repositories.AuthTokenRepository
import com.kbcoding.signIn.domain.repositories.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SignInRepositoriesModule {

    @Binds
    fun bindAuthRepository(
        authServiceRepository: AdapterAuthServiceImpl
    ): AuthServiceRepository

    @Binds
    fun bindAuthTokenRepository(
        authTokenRepository: AuthTokenRepositoryImpl
    ): AuthTokenRepository

    @Binds
    fun bindProfileRepository(
        profileRepository: ProfileRepositoryImpl
    ): ProfileRepository

}