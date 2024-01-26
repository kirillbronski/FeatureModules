package com.kbcoding.featuremodules.glue.navigation.di

import com.kbcoding.featuremodules.glue.navigation.repositories.AdapterGetCartItemCountRepository
import com.kbcoding.featuremodules.glue.navigation.repositories.AdapterGetCurrentUsernameRepository
import com.kbcoding.navigation.domain.repositories.GetCartItemCountRepository
import com.kbcoding.navigation.domain.repositories.GetCurrentUsernameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MainRepositoriesModule {

    @Binds
    fun bindGetCurrentUsernameRepository(
        getCurrentUsernameRepository: AdapterGetCurrentUsernameRepository
    ): GetCurrentUsernameRepository

    @Binds
    fun bindGetCartItemCountRepository(
        getCartItemCountRepository: AdapterGetCartItemCountRepository
    ): GetCartItemCountRepository

}