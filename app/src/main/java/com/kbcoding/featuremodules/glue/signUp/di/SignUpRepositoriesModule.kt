package com.kbcoding.featuremodules.glue.signUp.di

import com.kbcoding.featuremodules.glue.signUp.repositories.SignUpRepositoryImpl
import com.kbcoding.signUp.presentation.domain.repositories.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SignUpRepositoriesModule {

    @Binds
    fun bindSignUpDataSource(signUpDataSource: SignUpRepositoryImpl): SignUpRepository

}