package com.kbcoding.featuremodules.glue.signUp.di

import com.kbcoding.featuremodules.glue.signUp.SignUpRouterImpl
import com.kbcoding.signUp.presentation.SignUpRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface SignUpRouterModule {

    @Binds
    fun bindSignUpRouter(router: SignUpRouterImpl): SignUpRouter

}