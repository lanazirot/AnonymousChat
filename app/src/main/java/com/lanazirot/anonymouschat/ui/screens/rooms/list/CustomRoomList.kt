package com.lanazirot.anonymouschat.ui.screens.rooms.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.compose.state.channels.list.ChannelItemState
import io.getstream.chat.android.compose.ui.channels.list.ChannelItem
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable
fun CustomRoomList(channelItem: ChannelItemState, user: User?) {
    val navController = GlobalProvider.current.navController

    ChannelItem(
        channelItem = channelItem,
        currentUser = user,
        onChannelLongClick = {},
        onChannelClick = { channel ->
            navController.navigate("chat/${channel.cid}")
        },
        trailingContent = {
            Spacer(modifier = Modifier.width(8.dp))
        },
        centerContent = {
            Text(
                text = ChatTheme.channelNameFormatter.formatChannelName(it.channel, user),
                style = ChatTheme.typography.bodyBold,
                color = Color.White
            )
        }
    )
}