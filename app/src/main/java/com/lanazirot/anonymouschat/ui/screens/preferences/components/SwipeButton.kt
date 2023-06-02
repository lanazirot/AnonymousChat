package com.lanazirot.anonymouschat.ui.screens.preferences.components

import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.lanazirot.anonymouschat.ui.screens.preferences.ThemeViewModel

@Composable
fun DarkThemeSwitch(themeViewModel: ThemeViewModel) {
    val isDarkThemeEnabled by themeViewModel.isDarkThemeEnabled.collectAsState()

    Switch(
        checked = isDarkThemeEnabled,
        onCheckedChange = { checked ->
            themeViewModel.setDarkThemeEnabled(checked)
        }
    )
}