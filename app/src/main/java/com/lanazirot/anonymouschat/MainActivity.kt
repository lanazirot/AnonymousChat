package com.lanazirot.anonymouschat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lanazirot.anonymouschat.ui.providers.AppProvider
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.splashscreen.App
import com.lanazirot.anonymouschat.ui.theme.AnonymousChatTheme
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.lanazirot.anonymouschat.ui.screens.permissions.RequestPermission
import com.lanazirot.anonymouschat.ui.screens.preferences.PreferencesViewModel
import com.lanazirot.anonymouschat.ui.screens.preferences.ThemeViewModel
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("StateFlowValueCalledInComposition")
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProvideWindowInsets{
                val navController = rememberNavController()
                val gp = AppProvider(navController = navController)
                val preferencesViewModel : PreferencesViewModel = hiltViewModel()
                val language = preferencesViewModel.appLocale.collectAsState().value

                val themeViewModel: ThemeViewModel = hiltViewModel()
                val isDarkThemeEnabled by themeViewModel.isDarkThemeEnabled.collectAsState()

                AnonymousChatTheme(darkTheme = isDarkThemeEnabled) {
                    CompositionLocalProvider(
                        GlobalProvider provides gp
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.primaryVariant
                        ) {
                            LaunchedEffect(language) {
                                updateLocale(language)
                            }
                            RequestPermission(permission = Manifest.permission.ACCESS_FINE_LOCATION){
                                App(navController,this@MainActivity)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateLocale(locale: String) {
        resources.configuration.setLocale(Locale(locale))
        resources.configuration.locale = Locale(locale)
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
    }
}
