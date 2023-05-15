package com.lanazirot.anonymouschat.domain.models.app

import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lanazirot.anonymouschat.ui.screens.appScreen.RegisterScreen
import com.lanazirot.anonymouschat.domain.models.drawer.Drawer
import com.lanazirot.anonymouschat.ui.navigator.routes.AppScreens
import com.lanazirot.anonymouschat.ui.navigator.routes.DrawerScreens
import com.lanazirot.anonymouschat.ui.screens.appScreen.LoginScreen
import com.lanazirot.anonymouschat.ui.screens.chat.ChatScreen
import com.lanazirot.anonymouschat.ui.screens.drawer.Creditos
import com.lanazirot.anonymouschat.ui.screens.drawer.Dudas
import com.lanazirot.anonymouschat.ui.screens.drawer.Invitar
import com.lanazirot.anonymouschat.ui.screens.drawer.Politicas
import com.lanazirot.anonymouschat.ui.screens.drawer.Preferencias
import com.lanazirot.anonymouschat.ui.screens.rooms.RoomsScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(navController: NavHostController) {
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
                    navController.navigate(route) {
                        launchSingleTop = true
                    }
                }
            )
        },
        drawerBackgroundColor = Color.Black
    ) {
        NavHost(
            navController = navController,
            startDestination = AppScreens.Login.route
        ) {
            composable(AppScreens.Login.route) {
                LoginScreen()
            }

            composable(AppScreens.Register.route) {
                RegisterScreen()
            }

            composable("${AppScreens.Chat.route}/{channelId}") { backStackEntry ->
                ChatScreen(channelId = backStackEntry.arguments?.getString("channelId") ?: "")
            }

            composable("${DrawerScreens.Main.route}/{email}") { backStackEntry ->
                RoomsScreen(email = backStackEntry.arguments?.getString("email") ?: "")
            }

            composable(DrawerScreens.Share.route) {
                Invitar()
            }

            composable(DrawerScreens.About.route) {
                Dudas()
            }

            composable(DrawerScreens.Preferences.route) {
                Preferencias()
            }

            composable(DrawerScreens.Politics.route) {
                Politicas()
            }

            composable(DrawerScreens.Credits.route) {
                Creditos()
            }
        }
    }
}