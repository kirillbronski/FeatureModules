package com.kbcoding.featuremodules.glue.navigation

import com.kbcoding.featuremodules.R
import com.kbcoding.navigation.GlobalNavComponentRouter
import com.kbcoding.navigation.presentation.MainRouter
import javax.inject.Inject

class MainRouterImpl @Inject constructor(
    private val navComponentRouter: GlobalNavComponentRouter
) : MainRouter {

    override fun launchCart() {
        navComponentRouter.launch(R.id.cartListFragment)
    }

}