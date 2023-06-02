package com.lanazirot.anonymouschat.ui.screens.rooms.list

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.rooms.RoomsViewModel
import com.lanazirot.anonymouschat.ui.theme.Anonymous
import io.getstream.chat.android.compose.state.channels.list.ChannelItemState
import io.getstream.chat.android.compose.ui.components.avatar.ChannelAvatar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomRoomList(channelItem: ChannelItemState) {
    val navController = GlobalProvider.current.navController
    val roomsViewModel: RoomsViewModel = hiltViewModel()
    val user = roomsViewModel.getCurrentUser()
    val roomMines = roomsViewModel.getCurrentUser()?.id == channelItem.channel.createdBy.id

    var showCustomDialog by remember { mutableStateOf(false) }

    Card (
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .combinedClickable (
                onClick = {
                    navController.navigate("chat/${channelItem.channel.cid}")
                },
                onLongClick = {
                    if(roomMines) showCustomDialog = !showCustomDialog
                }
            )
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChannelAvatar(
                modifier = Modifier.size(40.dp),
                channel = channelItem.channel,
                currentUser = user,
                showOnlineIndicator = false,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = channelItem.channel.id,
                style = androidx.compose.ui.text.TextStyle(
                    fontFamily = Anonymous,
                    fontWeight = FontWeight.Black,
                    fontSize = 23.sp,
                    color = MaterialTheme.colors.primary
                )
            )

            if (roomMines) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.my_room),
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = Anonymous,
                        fontWeight = FontWeight.Black,
                        fontSize = 11.sp,
                        color = MaterialTheme.colors.primary
                    )
                )
            }
        }
    }

    if (showCustomDialog) {
        DeleteRoomDialog(
            channelID = channelItem.channel.cid,
            onDismissRequest = { showCustomDialog = !showCustomDialog },
        )
    }
}