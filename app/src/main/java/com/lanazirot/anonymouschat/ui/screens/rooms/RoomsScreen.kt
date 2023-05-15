package com.lanazirot.anonymouschat.ui.screens.rooms

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable
fun RoomsScreen(email :String) {
    val roomsViewModel : RoomsViewModel = hiltViewModel()
    val navController = GlobalProvider.current.navController

    roomsViewModel.connectUser(email)

    ChatTheme {
        ChannelsScreen(
            title = "Rooms",
            onItemClick = {
                channel -> navController.navigate("chat/${channel.cid}")
            }
        )
    }
}