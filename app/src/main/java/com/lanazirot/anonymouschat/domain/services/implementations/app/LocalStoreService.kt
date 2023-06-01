package com.lanazirot.anonymouschat.domain.services.implementations.app

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.lanazirot.anonymouschat.domain.constants.StorageConstants
import com.lanazirot.anonymouschat.domain.services.interfaces.app.ILocalStoreService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
class LocalStoreService(private val context: Context) : ILocalStoreService {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override val getStreamTokenAuth: Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[StorageConstants.STREAM_TOKEN_AUTH] ?: ""
        }

    override suspend fun setStreamTokenAuth(token: String) {
        context.dataStore.edit { preferences ->
            preferences[StorageConstants.STREAM_TOKEN_AUTH] = token
        }
    }

    override val getAppLocale: Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[StorageConstants.APP_LOCALE] ?: ""
        }

    override suspend fun setAppLocale(locale: String) {
        context.dataStore.edit { preferences ->
            preferences[StorageConstants.APP_LOCALE] = locale
        }
    }

    override val getDarkThemeState: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[StorageConstants.DARK_THEME_ENABLED] ?: false
        }

    override suspend fun setDarkThemeState(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[StorageConstants.DARK_THEME_ENABLED] = enabled
        }
    }
}