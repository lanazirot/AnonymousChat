package com.lanazirot.anonymouschat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.lanazirot.anonymouschat.ui.providers.AppProvider
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.splashscreen.App
import com.lanazirot.anonymouschat.ui.theme.AnonymousChatTheme
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.lanazirot.anonymouschat.ui.screens.permissions.RequestPermission

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val gp = AppProvider(navController = navController)

            AnonymousChatTheme {
                CompositionLocalProvider(
                    GlobalProvider provides gp
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black
                    ) {
                        RequestPermission(permission = Manifest.permission.ACCESS_FINE_LOCATION){
                            App(navController)
                        }
                    }
                }
            }
        }
    }
}
