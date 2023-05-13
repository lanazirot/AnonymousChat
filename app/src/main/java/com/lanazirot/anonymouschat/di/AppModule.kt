package com.lanazirot.anonymouschat.di

import android.content.Context
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.domain.services.implementations.AuthenticationService
import com.lanazirot.anonymouschat.domain.services.implementations.StreamService
import com.lanazirot.anonymouschat.domain.services.interfaces.IAuthenticationService
import com.lanazirot.anonymouschat.domain.services.interfaces.IStreamService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType
import io.getstream.chat.android.offline.plugin.configuration.Config
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideOfflinePluginFactory(@ApplicationContext context: Context) =
        StreamOfflinePluginFactory(
            config = Config(
                backgroundSyncEnabled = true,
                userPresence = true,
                persistenceEnabled = true,
                uploadAttachmentsNetworkType = UploadAttachmentsNetworkType.NOT_ROAMING
            ),
            appContext = context
        )

    @Singleton
    @Provides
    fun provideAuthenticationService(@ApplicationContext context: Context) : IAuthenticationService =
        AuthenticationService(
            context = context,
            offlinePluginFactory = provideOfflinePluginFactory(context)
        )

    @Singleton
    @Provides
    fun provideStreamService(@ApplicationContext context: Context) : IStreamService = StreamService(
        streamClient = provideAuthenticationService(context)
    )
}