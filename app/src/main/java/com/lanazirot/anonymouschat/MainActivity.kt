package com.lanazirot.anonymouschat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.lanazirot.anonymouschat.ui.theme.AnonymousChatTheme
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType
import io.getstream.chat.android.offline.plugin.configuration.Config
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import com.lanazirot.anonymouschat.domain.models.app.AppNavigation
import com.lanazirot.anonymouschat.ui.screens.chat.ChatScreen
import com.lanazirot.anonymouschat.ui.screens.rooms.RoomsScreen
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import androidx.compose.runtime.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TOKEN MANUAL PARA ACCESO AL CHAT
        val offlinePluginFactory = StreamOfflinePluginFactory(
            config = Config(
                backgroundSyncEnabled = true,
                userPresence = true,
                persistenceEnabled = true,
                uploadAttachmentsNetworkType = UploadAttachmentsNetworkType.NOT_ROAMING,
            ),
            appContext = applicationContext,
        )

        val client = ChatClient.Builder("szv7syqafk64", applicationContext)
            .withPlugin(offlinePluginFactory)
            .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
            .build()

//        val user = User(
//            id = "alanc",
//            name = "alanc",
//            image = "https://bit.ly/2TIt8NR"
//        )
//        client.connectUser(
//            user = user,
//            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiYWxhbmMifQ.PrfCcH1Fb8KrNIY8OZPza8N_v8M61Ixi-UhPos4EKEA"
//        ).enqueue()

        val user = User(
            id = "castro",
            name = "castro",
            image = "https://bit.ly/2TIt8NR"
        )
        client.connectUser(
            user = user,
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiY2FzdHJvIn0.lX5zevCLLZMmHBDp6zDpapgDDcI8Lmfv5nZmdD7J574"
        ).enqueue()

        setContent {
            ChatTheme {
                ChannelsScreen(
                    title = "Rooms",
                    onItemClick = {
                        channel -> startActivity(RoomsActivity.getIntent(this, channel.cid))
                    },
                    onBackPressed = {
                        finish()
                    }
                )
            }
        }

//        setContent {
//            AnonymousChatTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = Color.Black
//                ) {
//                    AppNavigation()
//                }
//            }
//        }
        hideSystemUI()
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView) ?: return
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}
