package com.kbcoding.featuremodules.glue.profile.repositories

import com.kbcoding.data.settings.SettingsDataSource
import com.kbcoding.profile.domain.repositories.AuthTokenRepository
import javax.inject.Inject

class AuthTokenRepositoryImpl @Inject constructor(
    private val settingsDataSource: SettingsDataSource,
) : AuthTokenRepository {

    override suspend fun clearToken() {
        settingsDataSource.setToken(null)
    }

}