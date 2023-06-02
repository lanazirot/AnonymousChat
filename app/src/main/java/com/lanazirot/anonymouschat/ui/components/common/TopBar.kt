package com.lanazirot.anonymouschat.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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