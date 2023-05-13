package com.lanazirot.anonymouschat.domain.models.app

import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lanazirot.anonymouschat.ui.screens.appScreen.RegisterScreen
import com.lanazirot.anonymouschat.domain.models.drawer.Drawer
import com.lanazirot.anonymouschat.domain.models.drawer.DrawerScreens
import com.lanazirot.anonymouschat.ui.navigator.AppScreens
import com.lanazirot.anonymouschat.ui.screens.appScreen.LoginScreen
import com.lanazirot.anonymouschat.ui.screens.drawer.Creditos
import com.lanazirot.anonymouschat.ui.screens.drawer.Dudas
import com.lanazirot.anonymouschat.ui.screens.drawer.Invitar
import com.lanazirot.anonymouschat.ui.screens.drawer.Politicas
import com.lanazirot.anonymouschat.ui.screens.drawer.Preferencias
import com.lanazirot.anonymouschat.ui.screens.rooms.RoomsScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(){
    val drawernavController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }
    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Drawer(
                onDestinationClicked = { route ->
                    scope.launch {
                        drawerState.close()
                    }
                    drawernavController.navigate(route) {
                        launchSingleTop = true
                    }
                }
            )
        },
        drawerBackgroundColor = Color.Black
    ) {
        NavHost(
            navController = drawernavController,
            startDestination = AppScreens.Login.route
        ) {
            composable(AppScreens.Login.route) {
                LoginScreen(navController = drawernavController)
            }

            composable(AppScreens.Register.route) {
                RegisterScreen(navController = drawernavController)
            }

            composable(DrawerScreens.Main.route) {
                RoomsScreen()
            }

            composable(DrawerScreens.Share.route) {
                Invitar(navController = drawernavController)
            }

            composable(DrawerScreens.About.route) {
                Dudas(navController = drawernavController)
            }

            composable(DrawerScreens.Preferences.route) {
                Preferencias(navController = drawernavController)
            }

            composable(DrawerScreens.Politics.route) {
                Politicas(navController = drawernavController)
            }

            composable(DrawerScreens.Credits.route) {
                Creditos(navController = drawernavController)
            }
        }
    }
}