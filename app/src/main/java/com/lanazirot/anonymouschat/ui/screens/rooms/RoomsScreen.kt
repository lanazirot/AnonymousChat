package com.lanazirot.anonymouschat.ui.screens.rooms

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lanazirot.anonymouschat.R
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable
fun RoomsScreen(onBackPressed: () -> Unit) {
    ChatTheme {
        ChannelsScreen(
            title = stringResource(id = R.string.app_name),
            isShowingSearch = true,
            onItemClick = { channel ->
                // TODO Start Messages Activity
            },
            onBackPressed = { onBackPressed() }
        )
    }
}