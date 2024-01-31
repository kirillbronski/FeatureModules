package com.kbcoding.featuremodules.glue.catalog.di

import com.kbcoding.catalog.presentation.CatalogFilterRouter
import com.kbcoding.catalog.presentation.CatalogRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import com.kbcoding.featuremodules.glue.catalog.AdapterCatalogFilterRouter
import com.kbcoding.featuremodules.glue.catalog.AdapterCatalogRouter

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouterModule {

    @Binds
    fun bindCatalogRouter(
        catalogRouter: AdapterCatalogRouter
    ): CatalogRouter

    @Binds
    fun bindCatalogFilterRouter(
        router: AdapterCatalogFilterRouter
    ): CatalogFilterRouter

}