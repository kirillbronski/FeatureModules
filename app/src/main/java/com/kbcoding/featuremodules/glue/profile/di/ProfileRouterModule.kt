package com.kbcoding.featuremodules.glue.profile.di

import com.kbcoding.profile.presentation.ProfileRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import com.kbcoding.featuremodules.glue.profile.ProfileRouterImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
interface ProfileRouterModule {

    @Binds
    fun bindProfileRouter(
        profileRouter: ProfileRouterImpl,
    ): ProfileRouter

}