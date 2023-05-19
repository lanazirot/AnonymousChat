package com.lanazirot.anonymouschat.ui.screens.rooms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lanazirot.anonymouschat.ui.screens.drawer.TopBar
import com.lanazirot.anonymouschat.ui.screens.rooms.list.CustomRoomList
import io.getstream.chat.android.compose.ui.channels.list.ChannelList
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable
fun RoomsScreen(openDrawer: () -> Unit) {
    val roomsViewModel : RoomsViewModel = hiltViewModel()
    val email = Firebase.auth.currentUser?.email ?: ""
    val user = roomsViewModel.getCurrentUser()

    roomsViewModel.connectUser(email)

    ChatTheme {
        Column {
            TopBar(
                title = "Anonymous Chat",
                icon = null,
                buttonIcon = Icons.Filled.Menu
            ) { openDrawer() }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    //TODO: Create new room
                }
            ) {
                Text(text = "Crear nueva sala")
            }

            ChannelList(
                itemContent = { channelItem ->
                    CustomRoomList(channelItem = channelItem, user = user)
                }
            )
        }
    }
}

