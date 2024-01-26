package ua.cn.stu.multimodule.glue.signin.di

import com.kbcoding.featuremodules.glue.signIn.AdapterSignInRouter
import com.kbcoding.signIn.presentation.SignInRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface SignInRouterModule {

    @Binds
    fun bindSignInRouter(router: AdapterSignInRouter): SignInRouter

}