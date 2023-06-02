package com.lanazirot.anonymouschat.ui.screens.rooms.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.rooms.RoomsViewModel
import com.lanazirot.anonymouschat.ui.theme.Anonymous
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.compose.state.channels.list.ChannelItemState
import io.getstream.chat.android.compose.ui.channels.list.ChannelItem
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import java.time.format.TextStyle

@Composable
fun CustomRoomList(channelItem: ChannelItemState) {
    val navController = GlobalProvider.current.navController
    val roomsViewModel: RoomsViewModel = hiltViewModel()
    val user = roomsViewModel.getCurrentUser()

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
                text = it.channel.name + " " + it.channel.id,
                style = androidx.compose.ui.text.TextStyle(
                    fontFamily = Anonymous,
                    fontWeight = FontWeight.Black,
                    fontSize = 23.sp,
                    color = MaterialTheme.colors.primary
                )
            )
        }
    )
}