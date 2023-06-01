package com.lanazirot.anonymouschat.ui.screens.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.components.common.ScrollableScreen
import com.lanazirot.anonymouschat.ui.components.common.TopBar
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider

@Composable
fun Dudas() {
    val navController = GlobalProvider.current.navController

<<<<<<< HEAD
    val listItems = listOf("General", "¿Que es Anonymous Chat?", "Anonymous Chat es una aplicación móvil que permite la creación de salas de chat comunitarias a las que cualquier individuo con la aplicación y a una distancia de x metros de quien creó la sala puede unirse."
        , "¿Quienes pueden enviarme mensaje?", "La gente cercana a tu ubicación"
        ,"¿Es posible que alguien me rastree a través de la ubicación que proporciono?", "No, tus datos no son revelados a otros usuarios."
        ,"¿La aplicación permite el intercambio de archivos o imágenes en los chats?","Anonymous Chat solo permite el intercambio de mensajes de texto"
        ,"¿Cómo puedo evitar que mi ubicación sea compartida con otros usuarios después de cerrar sesión en la aplicación?","Su ubicación no será compartida a otros usuarios y solo se usará mientras la app se encuentre activa")
=======
    val listItems = listOf(
        stringResource(R.string.general),
        stringResource(R.string.dudes_1),
        stringResource(R.string.dudes_2),
        stringResource(R.string.dudes_3),
        stringResource(R.string.dudes_4),
    )
>>>>>>> nuevodiseño

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = stringResource(R.string.dudes),
            icon = painterResource(R.drawable.idudas),
            buttonIcon = Icons.Filled.ArrowBack
        ) { navController.popBackStack() }
        ScrollableScreen(listItems)
    }
}