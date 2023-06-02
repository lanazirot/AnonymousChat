package com.lanazirot.anonymouschat.ui.screens.chat.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.screens.chat.ChatViewModel
import com.lanazirot.anonymouschat.ui.theme.Anonymous
import io.getstream.chat.android.client.models.Channel

@Composable
fun ChatHeader(channel: Channel, onBack: () -> Unit) {

    val chatViewModel: ChatViewModel = hiltViewModel()
    val chatState = chatViewModel.chatState.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(chatState.reported) {
        if (chatState.reported) {
            Toast.makeText(
                context,
                "Room was reported to AnonymousChat team. Thank you! and keep safe.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    onBack()
                }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Outlined.PeopleOutline,
                contentDescription = "",
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = channel.id,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.width(200.dp),
                maxLines = 1,
                style = TextStyle(
                    fontFamily = Anonymous,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Center
                ),
            )

            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    chatViewModel.reportRoom()
                }) {
                Icon(
                    imageVector = Icons.Outlined.Warning,
                    contentDescription = "Report",
                    tint = Color.Yellow
                )
            }
        }
    }
}