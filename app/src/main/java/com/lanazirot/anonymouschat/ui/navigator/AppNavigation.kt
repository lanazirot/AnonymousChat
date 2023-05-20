package com.lanazirot.anonymouschat.ui.navigator

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lanazirot.anonymouschat.ui.navigator.routes.AppScreens
import com.lanazirot.anonymouschat.ui.navigator.routes.DrawerScreens
import com.lanazirot.anonymouschat.ui.screens.appScreen.LoginScreen
import com.lanazirot.anonymouschat.ui.screens.appScreen.RegisterScreen
import com.lanazirot.anonymouschat.ui.screens.chat.ChatScreen
import com.lanazirot.anonymouschat.ui.screens.drawer.Creditos
import com.lanazirot.anonymouschat.ui.screens.drawer.Drawer
import com.lanazirot.anonymouschat.ui.screens.drawer.Dudas
import com.lanazirot.anonymouschat.ui.screens.drawer.Invitar
import com.lanazirot.anonymouschat.ui.screens.drawer.Politicas
import com.lanazirot.anonymouschat.ui.screens.drawer.Preferencias
import com.lanazirot.anonymouschat.ui.screens.rooms.RoomsScreen
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

enum class SwipeDirection(val raw: Int) {
    Left(0),
    Initial(1),
    Right(2),
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppNavigation(navController: NavHostController) {
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
                composable(DrawerScreens.Main.route) {
                    RoomsScreen(openDrawer = { openDrawer() })
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
}