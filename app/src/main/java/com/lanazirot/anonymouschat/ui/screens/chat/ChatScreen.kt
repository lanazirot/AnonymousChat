package com.lanazirot.anonymouschat.ui.screens.chat

import androidx.compose.runtime.Composable
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import io.getstream.chat.android.compose.ui.messages.MessagesScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable
fun ChatScreen(channelId : String) {
    val navController = GlobalProvider.current.navController

    ChatTheme {
        MessagesScreen(
            channelId = channelId,
            onBackPressed = { navController.popBackStack() }
        )
    }
}