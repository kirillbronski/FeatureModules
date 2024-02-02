package com.kbcoding.profile.presentation.profile

import com.kbcoding.common.Container
import com.kbcoding.presentation.BaseViewModel
import com.kbcoding.profile.domain.GetProfileUseCase
import com.kbcoding.profile.domain.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.kbcoding.profile.presentation.ProfileRouter
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val router: ProfileRouter,
) : BaseViewModel() {

    val profileLiveValue = getProfileUseCase.getProfile()
        .toLiveValue(Container.Pending)

    fun reload() = debounce {
        getProfileUseCase.reload()
    }

    fun logout() = debounce {
        viewModelScope.launch {
            logoutUseCase.logout()
            router.restartApp()
        }
    }

    fun editUsername() = debounce {
        router.launchEditUsername()
    }

}