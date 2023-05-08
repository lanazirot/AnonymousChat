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
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.screens.drawer.TopBar
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable
fun RoomsScreen(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Anonymous Chat",
            icon = painterResource(R.drawable.home),
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Buscando paginas jeje spinner......")
        }
    }

//    ChatTheme {
//        ChannelsScreen(
//            title = stringResource(id = R.string.app_name),
//            isShowingSearch = true,
//            onItemClick = { channel ->
//                // TODO Start Messages Activity
//            },
//            onBackPressed = { onBackPressed() }
//        )
//    }
}