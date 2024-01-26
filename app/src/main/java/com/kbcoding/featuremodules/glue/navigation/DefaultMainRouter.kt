package com.kbcoding.featuremodules.glue.navigation

import com.kbcoding.navigation.GlobalNavComponentRouter
import com.kbcoding.navigation.presentation.MainRouter
import javax.inject.Inject

class DefaultMainRouter @Inject constructor(
    private val navComponentRouter: GlobalNavComponentRouter
) : MainRouter {

    // TODO: implemented
    override fun launchCart() {
        //navComponentRouter.launch(R.id.cartListFragment)
    }

}