package com.lanazirot.anonymouschat.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageFromUrl(imageUrl: String, modifier: Modifier) {
    val painter = rememberAsyncImagePainter(imageUrl)
    Image(
        painter = painter,
        contentDescription = "img",
        modifier = modifier
    )
}
