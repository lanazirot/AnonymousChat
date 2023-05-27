package com.lanazirot.anonymouschat.ui.screens.loading

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun LoadingScreen() {
    Text(
        text = "Cargando...",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(fontSize = 24.sp, color = Color.White)
    )
}