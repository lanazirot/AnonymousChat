package com.lanazirot.anonymouschat.ui.screens.rooms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.RoomsActivity
import com.lanazirot.anonymouschat.ui.screens.drawer.TopBar
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable
fun RoomsScreen() {
    val roomsViewModel : RoomsViewModel = hiltViewModel()
    roomsViewModel.connectUser()

    ChatTheme {
        ChannelsScreen(
            title = "Rooms",
            onItemClick = {
//                    channel -> startActivity(RoomsActivity.getIntent(this, channel.cid))
            },
            onBackPressed = {
//                finish()
            }
        )
    }
}