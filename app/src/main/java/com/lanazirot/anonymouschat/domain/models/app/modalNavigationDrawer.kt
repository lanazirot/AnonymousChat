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
import com.lanazirot.anonymouschat.ui.screens.appScreen.PostSplashScreen
import com.lanazirot.anonymouschat.ui.screens.drawerScreen.Creditos
import com.lanazirot.anonymouschat.ui.screens.drawerScreen.Dudas
import com.lanazirot.anonymouschat.ui.screens.drawerScreen.Invitar
import com.lanazirot.anonymouschat.ui.screens.drawerScreen.PantallaPrincipal
import com.lanazirot.anonymouschat.ui.screens.drawerScreen.Politicas
import com.lanazirot.anonymouschat.ui.screens.drawerScreen.Preferencias
import kotlinx.coroutines.launch

@Composable
fun appNavigation(){
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
                PostSplashScreen(navController = drawernavController)
            }

            composable(AppScreens.Register.route) {
                RegisterScreen(navController = drawernavController)
            }

            composable(DrawerScreens.PantallaP.route) {
                PantallaPrincipal(openDrawer = { openDrawer() })
            }

            composable(DrawerScreens.Invitar.route) {
                Invitar(navController = drawernavController)
            }
            composable(DrawerScreens.Dudas.route) {
                Dudas(navController = drawernavController)
            }
            composable(DrawerScreens.Preferencias.route) {
                Preferencias(navController = drawernavController)
            }
            composable(DrawerScreens.Politicas.route) {
                Politicas(navController = drawernavController)
            }
            composable(DrawerScreens.Creditos.route) {
                Creditos(navController = drawernavController)
            }
        }
    }
}