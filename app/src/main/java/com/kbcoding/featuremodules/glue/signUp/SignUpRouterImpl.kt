package com.kbcoding.featuremodules.glue.signUp

import com.kbcoding.navigation.GlobalNavComponentRouter
import com.kbcoding.signUp.presentation.SignUpRouter
import javax.inject.Inject

class SignUpRouterImpl @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
) : SignUpRouter {

    override fun goBack() {
        globalNavComponentRouter.pop()
    }

}