package com.lanazirot.anonymouschat.ui.providers

import android.annotation.SuppressLint
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

data class AppProvider (
    val navController: NavHostController
)

@SuppressLint("CompositionLocalNaming")
val GlobalProvider = compositionLocalOf<AppProvider> { error("No navigation host controller provided.") }