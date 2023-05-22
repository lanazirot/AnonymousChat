package com.lanazirot.anonymouschat.ui.screens.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.components.ScrollableScreen
import com.lanazirot.anonymouschat.ui.components.TopBar
import com.lanazirot.anonymouschat.ui.navigator.routes.DrawerScreens
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider

@Composable
fun Dudas() {
    val navController = GlobalProvider.current.navController

    val listItems = listOf("General", "¿Que es Anonymous Chat?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris elementum porttitor dapibus."
        , "¿Quienes pueden enviarme mensaje?", "Praesent suscipit semper risus non aliquam. Aenean non ante eu ligula vehicula vehicula. Quisque sodales tincidunt mattis."
        , "¿Como se maneja la privacidad en  Anonymous Chat?", "Cras varius lacus ac eros vulputate vulputate. Nunc nec blandit quam, vitae convallis elit. Sed rutrum placerat justo eget rutrum."
        ,"¿Que son los nombres de usuario? ¿Como se eligen?","Cras varius lacus ac eros vulputate vulputate. Nunc nec blandit quam, vitae convallis elit. Sed rutrum placerat justo eget rutrum.")

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Dudas",
            icon = painterResource(R.drawable.idudas),
            buttonIcon = Icons.Filled.ArrowBack
        ) { navController.navigate(DrawerScreens.Main.route)}
        ScrollableScreen(listItems)
    }
}