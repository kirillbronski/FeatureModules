package com.kbcoding.featuremodules.glue.signIn


import com.kbcoding.featuremodules.R
import com.kbcoding.navigation.GlobalNavComponentRouter
import com.kbcoding.signIn.presentation.SignInRouter
import com.kbcoding.signUp.presentation.signUp.SignUpFragment
import javax.inject.Inject

class SignInRouterImpl @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
) : SignInRouter {

    override fun launchSignUp(email: String) {
        val screen = SignUpFragment.Screen(email)
        globalNavComponentRouter.launch(R.id.signUpFragment, screen)
    }

    override fun launchMain() {
        globalNavComponentRouter.startTabs()
    }

}