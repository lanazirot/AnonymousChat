package com.lanazirot.anonymouschat.domain.constants

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object StorageConstants {
    val STREAM_TOKEN_AUTH = stringPreferencesKey("stream_token_auth")
    val APP_LOCALE = stringPreferencesKey("app_locale")
    val DARK_THEME_ENABLED = booleanPreferencesKey("dark_theme_enabled")
}