package com.lanazirot.anonymouschat.ui.screens.chat

import androidx.compose.runtime.Composable
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.compose.ui.messages.MessagesScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType
import io.getstream.chat.android.offline.plugin.configuration.Config
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory

@Composable
fun ChatScreen(channelId: String) {
    ChatTheme {
        MessagesScreen(
            channelId = channelId,
            onBackPressed = { /* TODO */ },
            onHeaderActionClick = { /* TODO */ }
        )
    }
}