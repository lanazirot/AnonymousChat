package com.lanazirot.anonymouschat.ui.screens.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Ro
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.components.common.TopBar
import com.lanazirot.anonymouschat.ui.navigator.routes.AppScreens
import com.lanazirot.anonymouschat.ui.navigator.routes.DrawerScreens
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.preferences.components.ToggleButtonLanguage

@Composable
fun PreferencesScreen() {
    val navController = GlobalProvider.current.navController
    val preferencesViewModel :PreferencesViewModel = hiltViewModel()
    val language by preferencesViewModel.appLocale.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = stringResource(R.string.preferences_app),
            icon = painterResource(R.drawable.ipreferencias),
            buttonIcon = Icons.Filled.ArrowBack
        ) { navController.popBackStack() }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Text(text = stringResource(R.string.language), color = Color.White)
                ToggleButtonLanguage(currentLanguage = language, onChange = { locale ->
                    preferencesViewModel.setLocale(locale)
                    navController.navigate(DrawerScreens.Main.route)
                })
            }
        }
    }
}