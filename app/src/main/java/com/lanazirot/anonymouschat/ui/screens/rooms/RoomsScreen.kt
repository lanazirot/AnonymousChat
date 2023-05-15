package com.lanazirot.anonymouschat.ui.screens.rooms

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable
fun RoomsScreen(navController: NavController) {
    val roomsViewModel : RoomsViewModel = hiltViewModel()
    roomsViewModel.connectUser()

    ChatTheme {
        ChannelsScreen(
            title = "Rooms",
            onItemClick = {
                channel -> navController.navigate("chat/${channel.cid}")
            }
        )
    }
}