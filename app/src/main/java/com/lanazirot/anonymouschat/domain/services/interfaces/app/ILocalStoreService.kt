package com.lanazirot.anonymouschat.domain.services.interfaces.app

import kotlinx.coroutines.flow.Flow

interface ILocalStoreService {
    // Stream
    val getStreamTokenAuth: Flow<String>
    suspend fun setStreamTokenAuth(token: String)

    // App
    val getAppLocale: Flow<String>
    suspend fun setAppLocale(locale: String)

    // Theme
    val getDarkThemeState: Flow<Boolean>
    suspend fun setDarkThemeState(enabled: Boolean)
}
