package com.lanazirot.anonymouschat.ui.screens.preferences

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.components.common.TopBar
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.preferences.components.DarkThemeSwitch
import com.lanazirot.anonymouschat.ui.screens.preferences.components.ToggleButtonLanguage

@Composable
fun PreferencesScreen() {
    val navController = GlobalProvider.current.navController
    val preferencesViewModel :PreferencesViewModel = hiltViewModel()
    val language by preferencesViewModel.appLocale.collectAsState()
    val themeViewModel: ThemeViewModel = hiltViewModel()

    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(
            title = stringResource(R.string.preferences_app),
            icon = painterResource(R.drawable.ipreferencias),
            buttonIcon = Icons.Filled.ArrowBack
        ) { navController.popBackStack() }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
    //            .background(MaterialTheme.colors.secondary),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(R.string.language), color = MaterialTheme.colors.primary, fontSize = 23.sp)
                ToggleButtonLanguage(currentLanguage = language, onChange = { locale ->
                    preferencesViewModel.setLocale(locale)
 //                   navController.navigate(DrawerScreens.Main.route)
                })
            }
            Row(modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
    //            .background(MaterialTheme.colors.secondary),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(R.string.theme), color = MaterialTheme.colors.primary, fontSize = 23.sp)
                DarkThemeSwitch(themeViewModel)
            }
        }
    }
}
