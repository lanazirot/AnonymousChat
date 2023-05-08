package com.lanazirot.anonymouschat

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.messages.MessagesScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import android.content.Intent as Intent1

class RoomsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val channelId = intent.getStringExtra(KEY_CHANNEL_ID)

        if(channelId == null) {
            finish()
            return
        }

        setContent {
            ChatTheme {
                MessagesScreen(
                    channelId = channelId,
                    onBackPressed = { finish() }
                )
            }
        }
    }

    companion object {
        private const val KEY_CHANNEL_ID = "channel_id"

        fun getIntent(context: Context, channelId: String): Intent1 {
            return Intent1(context, RoomsActivity::class.java).apply {
                putExtra(KEY_CHANNEL_ID, channelId)
            }
        }
    }
}