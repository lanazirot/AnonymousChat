package com.lanazirot.anonymouschat.ui.screens.rooms


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lanazirot.anonymouschat.ui.screens.drawer.TopBar
import com.lanazirot.anonymouschat.ui.screens.rooms.list.CustomRoomList
import com.lanazirot.anonymouschat.ui.theme.Anonymous
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
            Spacer(modifier = Modifier.height(10.dp))
            TopBar(
                title = "Anonymous Chat",
                icon = null,
                buttonIcon = Icons.Filled.Menu
            ) { openDrawer() }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    //TODO: Create new room
                }
            ) {
                Text(text = "Crear nueva sala", color = Color.Black)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(500.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(text = "Buscando salas", fontFamily = Anonymous, fontWeight = FontWeight.Normal, fontSize = 26.sp, color = Color.White)
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "🤫", fontFamily = Anonymous, fontWeight = FontWeight.Normal, fontSize = 50.sp, color = Color.White)
                Spacer(modifier = Modifier.height(15.dp))
                ChannelList(
                    itemContent = { channelItem ->
                        CustomRoomList(channelItem = channelItem, user = user)
                    }
                )
            }

        }
    }
}

