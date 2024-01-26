package com.kbcoding.featuremodules.glue.signIn


import com.kbcoding.navigation.GlobalNavComponentRouter
import com.kbcoding.signIn.presentation.SignInRouter
import javax.inject.Inject

class AdapterSignInRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
) : SignInRouter {

    // TODO: implemented
    override fun launchSignUp(email: String) {
//        val screen = SignUpFragment.Screen(email)
//        globalNavComponentRouter.launch(R.id.signUpFragment, screen)
    }

    override fun launchMain() {
        globalNavComponentRouter.startTabs()
    }

}