package com.lanazirot.anonymouschat.ui.screens.chat

import androidx.compose.runtime.Composable
import io.getstream.chat.android.compose.ui.messages.MessagesScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable
fun ChatScreen(channelId : String) {
    ChatTheme {
        MessagesScreen(
            channelId = channelId,
            onBackPressed = {  }
        )
    }
}