package com.kbcoding.featuremodules.glue.signIn.di

import com.kbcoding.featuremodules.glue.signIn.repositories.AdapterAuthServiceRepository
import com.kbcoding.featuremodules.glue.signIn.repositories.AdapterAuthTokenRepository
import com.kbcoding.featuremodules.glue.signIn.repositories.AdapterProfileRepository
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
        authServiceRepository: AdapterAuthServiceRepository
    ): AuthServiceRepository

    @Binds
    fun bindAuthTokenRepository(
        authTokenRepository: AdapterAuthTokenRepository
    ): AuthTokenRepository

    @Binds
    fun bindProfileRepository(
        profileRepository: AdapterProfileRepository
    ): ProfileRepository

}