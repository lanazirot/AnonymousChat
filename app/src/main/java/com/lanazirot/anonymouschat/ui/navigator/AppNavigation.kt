package com.lanazirot.anonymouschat.ui.navigator

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lanazirot.anonymouschat.MainActivity
import com.lanazirot.anonymouschat.ui.navigator.routes.AppScreens
import com.lanazirot.anonymouschat.ui.navigator.routes.DrawerScreens
import com.lanazirot.anonymouschat.ui.screens.login.LoginScreen
import com.lanazirot.anonymouschat.ui.screens.register.RegisterScreen
import com.lanazirot.anonymouschat.ui.screens.chat.ChatScreen
import com.lanazirot.anonymouschat.ui.screens.drawer.*
import com.lanazirot.anonymouschat.ui.screens.preferences.PreferencesScreen
import com.lanazirot.anonymouschat.ui.screens.rooms.RoomsScreen
import kotlinx.coroutines.launch

enum class SwipeDirection(val raw: Int) {
    Left(0),
    Initial(1),
    Right(2),
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppNavigation(navController: NavHostController, mainActivity: MainActivity) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }

    var size by remember { mutableStateOf(Size.Zero) }
    val swipeableState = rememberSwipeableState(SwipeDirection.Initial)
    val density = LocalDensity.current
    val boxSize = 120.dp
    val width = remember(size) {
        if (size.width == 0f) {
            1f
        } else {
            size.width - with(density) { boxSize.toPx() }
        }
    }
    if (swipeableState.isAnimationRunning) {
        DisposableEffect(Unit) {
            onDispose {
                when (swipeableState.currentValue) {
                    SwipeDirection.Right -> {
                        if (navController.currentBackStackEntry?.destination?.route in listOf(AppScreens.Login.route, AppScreens.Register.route)) {
                            scope.launch {
                                drawerState.close()
                            }
                        } else {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    }
                    SwipeDirection.Left -> {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                    else -> {
                        return@onDispose
                    }
                }
                scope.launch {
                    swipeableState.animateTo(SwipeDirection.Initial)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { size = Size(it.width.toFloat(), it.height.toFloat()) }
            .swipeable(
                state = swipeableState,
                anchors = mapOf(
                    0f to SwipeDirection.Left,
                    width / 2 to SwipeDirection.Initial,
                    width to SwipeDirection.Right,
                ),
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.Black)
    ) {
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    modifier = Modifier.fillMaxSize(),
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route)
                    },
                    onCloseDrawer = { // Nueva función de callback para cerrar el drawer
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    mainActivity
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
                composable(DrawerScreens.Main.route) {
                    RoomsScreen(openDrawer = { openDrawer() })
                }

                composable(DrawerScreens.About.route) {
                    Dudas()
                }

                composable(DrawerScreens.Preferences.route) {
                    PreferencesScreen()
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
}