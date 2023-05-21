package com.lanazirot.anonymouschat.ui.screens.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanazirot.anonymouschat.domain.models.drawer.ScrollableScreen
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.theme.Anonymous

@Composable
fun TopBar(title: String = "", buttonIcon: ImageVector, icon: Painter?, onButtonClicked: () -> Unit) {
    val tittleStyled = if (icon != null) "      $title" else title

    TopAppBar(
        title = {
            if (icon != null) {
                Image(
                    painter = icon,
                    contentDescription = "null",
                    modifier = Modifier.size(25.dp)
                )
            }

            Text(
                text = tittleStyled,
                color = Color.White,
                fontFamily = Anonymous,
                fontWeight = FontWeight.Black,
                fontSize = 25.sp
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() } ) {
                Icon(buttonIcon, contentDescription = "", tint = Color.White)
            }
        },
        backgroundColor = Color.Black
    )
}
