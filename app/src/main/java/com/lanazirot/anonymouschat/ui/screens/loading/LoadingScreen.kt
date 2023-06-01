package com.lanazirot.anonymouschat.ui.screens.loading

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.lanazirot.anonymouschat.R

@Composable
fun LoadingScreen() {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context)
                .data(data = R.drawable.cargando)
                .apply {
                    size(Size.ORIGINAL)
                }
                .build(),
            imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center).testTag("LoadingScreenLogo")
    )
}