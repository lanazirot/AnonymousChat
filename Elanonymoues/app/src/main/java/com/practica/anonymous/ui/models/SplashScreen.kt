package com.practica.anonymous.ui.models

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.practica.anonymous.R

@Composable
fun SplashScreenAnony(){
    //A column with two big images. One is the logo and the other is the name of the app

    Column (
        modifier = Modifier.fillMaxWidth().background(color = Color.Black), horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ,
        verticalArrangement = Arrangement.Center

            ){
        //Logo
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo", modifier = Modifier.fillMaxSize(0.6f))

        //Name of the app
        Image(painter = painterResource(id = R.drawable.nombre) , contentDescription = "Nombre",modifier = Modifier.fillMaxSize(0.6f))
    }
}