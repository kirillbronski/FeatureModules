package com.kbcoding.featuremodules.glue.profile

import com.kbcoding.common.AppRestarter
import com.kbcoding.featuremodules.R
import com.kbcoding.navigation.GlobalNavComponentRouter
import com.kbcoding.profile.presentation.ProfileRouter
import javax.inject.Inject

class ProfileRouterImpl @Inject constructor(
    private val appRestarter: AppRestarter,
    private val globalNavComponentRouter: GlobalNavComponentRouter,
) : ProfileRouter {

    override fun launchEditUsername() {
        globalNavComponentRouter.launch(R.id.editProfileFragment)
    }

    override fun goBack() {
        globalNavComponentRouter.pop()
    }

    override fun restartApp() {
        appRestarter.restartApp()
    }
}