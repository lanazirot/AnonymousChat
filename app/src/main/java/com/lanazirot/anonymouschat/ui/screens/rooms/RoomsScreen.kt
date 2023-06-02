package com.lanazirot.anonymouschat.ui.screens.rooms

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.components.common.TopBar
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.preferences.ThemeViewModel
import com.lanazirot.anonymouschat.ui.screens.rooms.list.CustomRoomList
import io.getstream.chat.android.compose.ui.channels.list.ChannelList
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable
fun RoomsScreen(openDrawer: () -> Unit) {
    val roomsViewModel: RoomsViewModel = hiltViewModel()
    val themeViewModel: ThemeViewModel = hiltViewModel()
    val isDarkThemeEnabled by themeViewModel.isDarkThemeEnabled.collectAsState()

    val roomsState = roomsViewModel.roomsState.collectAsState().value
    val navController = GlobalProvider.current.navController

    val addIcon = Icons.Filled.Add

    LaunchedEffect(roomsState.transported) {
        if (roomsState.transported) {
            navController.navigate("chat/${roomsState.roomToBeTransported}")
        }
    }

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    ChatTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.primaryVariant
        ) {
            Column(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
                TopBar(
                    title = "Anonymous Chat",
                    icon = null,
                    buttonIcon = Icons.Filled.Menu
                ) { openDrawer() }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.width(200.dp),
                        onClick = {
                            roomsViewModel.createRoom()
                        }
                    ) {
                        Icon(addIcon, contentDescription = "Agregar")
                        Text(
                            text = stringResource(R.string.new_room),
                            color = MaterialTheme.colors.surface,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ChannelList(
                        itemContent = { channelItem ->
                            CustomRoomList(channelItem = channelItem)
                        },
                        emptyContent = {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(context)
                                        .data(
                                            data = if (isDarkThemeEnabled) {
                                                R.drawable.buscandosala
                                            } else {
                                                R.drawable.buscandosalaclaro
                                            }
                                        )
                                        .apply {
                                            size(Size(432, 432))
                                        }
                                        .build(),
                                    imageLoader = imageLoader
                                ),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
                            )
                        }
                    )
                }
            }
        }
    }
}