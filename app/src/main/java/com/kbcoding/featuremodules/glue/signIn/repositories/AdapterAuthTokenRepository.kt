package com.kbcoding.featuremodules.glue.signIn.repositories

import com.kbcoding.data.settings.SettingsDataSource
import com.kbcoding.signIn.domain.repositories.AuthTokenRepository
import javax.inject.Inject

class AdapterAuthTokenRepository @Inject constructor(
    private val settingsDataSource: SettingsDataSource,
) : AuthTokenRepository {

    override suspend fun setToken(token: String?) {
        settingsDataSource.setToken(token)
    }

    override suspend fun getToken(): String? {
        return settingsDataSource.getToken()
    }
}