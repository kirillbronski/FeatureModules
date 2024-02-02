package com.kbcoding.featuremodules.glue.catalog

import com.kbcoding.catalog.domain.entities.ProductFilter
import com.kbcoding.catalog.presentation.CatalogFilterRouter
import com.kbcoding.catalog.presentation.filter.CatalogFilterFragment
import com.kbcoding.common.ScreenCommunication
import com.kbcoding.common.listen
import com.kbcoding.featuremodules.R
import com.kbcoding.navigation.GlobalNavComponentRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatalogFilterRouterImpl @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter,
    private val screenCommunication: ScreenCommunication,
) : CatalogFilterRouter {

    override fun launchFilter(initialFilter: ProductFilter) {
        globalNavComponentRouter.launch(
            R.id.catalogFilterFragment,
            CatalogFilterFragment.Screen(initialFilter)
        )
    }

    override fun receiveFilterResults(): Flow<ProductFilter> {
        return screenCommunication.listen(ProductFilter::class.java)
    }

    override fun sendFilterResults(filter: ProductFilter) {
        screenCommunication.publishResult(filter)
    }

    override fun goBack() {
        globalNavComponentRouter.pop()
    }

    override fun registerBackHandler(scope: CoroutineScope, handler: () -> Boolean) {
        globalNavComponentRouter.registerBackHandler(scope, handler)
    }

}