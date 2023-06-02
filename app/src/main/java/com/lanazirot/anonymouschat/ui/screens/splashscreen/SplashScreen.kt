package com.lanazirot.anonymouschat.ui.screens.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.lanazirot.anonymouschat.MainActivity
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.navigator.AppNavigation
import kotlinx.coroutines.delay

@Composable
fun App(navController: NavHostController, mainActivity: MainActivity) {
    val isLoaded = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2000)
        isLoaded.value = true
    }

    if (isLoaded.value) {
        AppNavigation(navController,mainActivity)
    } else {
        SplashScreen()
    }
}


@Composable
fun SplashScreen(){

    Column (
        modifier = Modifier.fillMaxWidth().background(color = Color.Black), horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

            ){
        //Logo
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo", modifier = Modifier.fillMaxSize(0.6f).testTag("SplashScreenLogo"))

        //Name of the app
        Image(painter = painterResource(id = R.drawable.nombre) , contentDescription = "Nombre",modifier = Modifier.fillMaxSize(0.6f))
    }
}