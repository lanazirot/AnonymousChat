package com.lanazirot.anonymouschat.ui.screens.rooms.list


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.screens.rooms.RoomsViewModel

@Composable
fun DeleteRoomDialog(channelID: String, onDismissRequest: () -> Unit) {
    Dialog (
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .height(120.dp),
            elevation = 4.dp,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(10.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                ContentDialog(channelID, onDismissRequest)
            }
        }
    }
}

@Composable
fun ContentDialog(channelID: String, onDismissRequest: () -> Unit) {
    val roomsViewModel: RoomsViewModel = hiltViewModel()

    val deleteIcon = Icons.Outlined.Delete
    val closeIcon = Icons.Outlined.Close

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                roomsViewModel.deleteRoom(channelID)
                onDismissRequest()
            }
        ) {
            Icon(deleteIcon, contentDescription = "Eliminar", tint = Color.Red)
            Text(text = stringResource(R.string.delete_room), color= Color.Red)
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onDismissRequest()
            }
        ) {
            Icon(closeIcon, contentDescription = "Cancelar")
            Text(text = stringResource(R.string.cancel))
        }
    }
}