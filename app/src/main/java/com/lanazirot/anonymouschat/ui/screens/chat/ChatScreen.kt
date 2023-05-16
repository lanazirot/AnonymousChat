package com.lanazirot.anonymouschat.ui.screens.chat

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.drawer.TopBar
import io.getstream.chat.android.compose.ui.messages.MessagesScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.ui.theme.StreamColors
import io.getstream.chat.android.compose.ui.theme.StreamShapes

@Composable
fun ChatScreen(channelId: String) {
    val navController = GlobalProvider.current.navController

    ChatTheme(
        shapes = StreamShapes.defaultShapes().copy( // Customizing the shapes
            avatar = RoundedCornerShape(8.dp),
            attachment = RoundedCornerShape(16.dp),
            inputField = RectangleShape,
            myMessageBubble = RoundedCornerShape(16.dp),
            otherMessageBubble = RoundedCornerShape(16.dp),
            bottomSheet = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ),
        colors = StreamColors.defaultDarkColors(),
        isInDarkMode = true,
    ) {
        TopAppBar(
            title = { Text("Chat") },
            navigationIcon = {
                IconButton(onClick = { /* TODO */ }) {
                    Icon(Icons.Filled.Menu, contentDescription = null)
                }
            }
        )
        MessagesScreen(
            channelId = channelId,
            onBackPressed = { navController.popBackStack() }
        )
    }
}