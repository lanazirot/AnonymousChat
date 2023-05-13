package com.lanazirot.anonymouschat.domain.services.implementations

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.domain.services.interfaces.IAuthenticationService
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel

import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import javax.inject.Inject

class AuthenticationService @Inject constructor(
    private val context : Context,
    private val offlinePluginFactory: StreamOfflinePluginFactory
) : IAuthenticationService {
    override fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    override fun provideStreamClient(): ChatClient {
        return ChatClient.Builder(context.getString(R.string.stream_app_key), context)
            .withPlugin(offlinePluginFactory)
            .logLevel(ChatLogLevel.ALL)
            .build()
    }
}