package com.kbcoding.featuremodules.glue.profile.di

import com.kbcoding.profile.domain.repositories.AuthTokenRepository
import com.kbcoding.profile.domain.repositories.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kbcoding.featuremodules.glue.profile.repositories.AuthTokenRepositoryImpl
import com.kbcoding.featuremodules.glue.profile.repositories.ProfileRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface ProfileRepositoriesModule {

    @Binds
    fun bindAuthTokenRepository(
        authTokenRepository: AuthTokenRepositoryImpl
    ): AuthTokenRepository

    @Binds
    fun bindProfileRepository(
        profileRepository: ProfileRepositoryImpl
    ): ProfileRepository

}