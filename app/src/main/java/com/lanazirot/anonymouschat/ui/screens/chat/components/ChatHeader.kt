package com.lanazirot.anonymouschat.ui.screens.chat.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.theme.Anonymous
import io.getstream.chat.android.client.models.Channel

@Composable
fun ChatHeader(channel: Channel, onBack : () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row {
            Button(onClick = {
                onBack()
            }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = Color.Red
                )
            }
            Text(
                text = channel.id,
                style = TextStyle(
                    fontFamily = Anonymous,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Red
                ),
            )
        }
    }
}