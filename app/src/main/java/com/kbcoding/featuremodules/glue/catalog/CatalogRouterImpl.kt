package com.kbcoding.featuremodules.glue.catalog

import com.kbcoding.catalog.presentation.CatalogRouter
import com.kbcoding.catalog.presentation.details.ProductDetailsFragment
import com.kbcoding.featuremodules.R
import com.kbcoding.navigation.GlobalNavComponentRouter
import javax.inject.Inject

class CatalogRouterImpl @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter,
) : CatalogRouter {

    override fun launchDetails(productId: Long) {
        globalNavComponentRouter.launch(
            R.id.productDetailsFragment,
            ProductDetailsFragment.Screen(productId)
        )
    }

    // TODO: implemented create order fragment
    override fun launchCreateOrder() {
        //globalNavComponentRouter.launch(R.id.createOrderFragment)
    }

}