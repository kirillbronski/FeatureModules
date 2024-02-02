package com.kbcoding.featuremodules.glue.catalog.di

import com.kbcoding.catalog.presentation.CatalogFilterRouter
import com.kbcoding.catalog.presentation.CatalogRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import com.kbcoding.featuremodules.glue.catalog.CatalogFilterRouterImpl
import com.kbcoding.featuremodules.glue.catalog.CatalogRouterImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouterModule {

    @Binds
    fun bindCatalogRouter(
        catalogRouter: CatalogRouterImpl
    ): CatalogRouter

    @Binds
    fun bindCatalogFilterRouter(
        router: CatalogFilterRouterImpl
    ): CatalogFilterRouter

}