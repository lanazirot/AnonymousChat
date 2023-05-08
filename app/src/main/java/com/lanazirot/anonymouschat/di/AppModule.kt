package com.lanazirot.anonymouschat.di

import android.content.Context
import com.lanazirot.anonymouschat.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    @Provides
//    fun provideOfflinePluginFactory(@ApplicationContext context: Context) =
//        StreamOfflinePluginFactory(
//            config = Config(
//                backgroundSyncEnabled = true,
//                userPresence = true,
//                persistenceEnabled = true,
//                uploadAttachmentsNetworkType = UploadAttachmentsNetworkType.NOT_ROAMING
//            ),
//            appContext = context
//        )

    @Singleton
    @Provides
    fun provideChatClient(@ApplicationContext c: Context) =
        ChatClient.Builder(c.getString(R.string.api_key), c)
//            .withPlugin(offlinePluginFactory)
            .logLevel(ChatLogLevel.ALL)
            .build()
}