package com.lanazirot.anonymouschat.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.di.RepositoriesModule.provideAuthRepository
import com.lanazirot.anonymouschat.di.RepositoriesModule.provideChannelRepository
import com.lanazirot.anonymouschat.di.RepositoriesModule.provideUserRepository
import com.lanazirot.anonymouschat.domain.services.implementations.app.AuthenticationService
import com.lanazirot.anonymouschat.domain.services.implementations.app.LocalStoreService
import com.lanazirot.anonymouschat.domain.services.implementations.app.StreamService
import com.lanazirot.anonymouschat.domain.services.interfaces.app.IAuthenticationService
import com.lanazirot.anonymouschat.domain.services.interfaces.app.IStreamService
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IAuthAPI
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IChannelAPI
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IUserAPI
import com.lanazirot.anonymouschat.domain.services.interfaces.app.ILocalStoreService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    fun provideStreamService(
        @ApplicationContext context: Context,
        userAPI: IUserAPI,
        authAPI: IAuthAPI,
        channelAPI: IChannelAPI
    ): IStreamService = StreamService(
        streamClient = provideAuthenticationService(context),
        userRepository = provideUserRepository(userAPI),
        authRepository = provideAuthRepository(authAPI),
        channelRepository = provideChannelRepository(channelAPI)
    )

    @Provides
    @Singleton
    fun provideGoogleSignInClient(@ApplicationContext applicationContext: Context): GoogleSignInClient {
        val mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(applicationContext.getString(R.string.google_token))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(applicationContext, mGoogleSignInOptions)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideLocalStoreService(@ApplicationContext context: Context) :ILocalStoreService = LocalStoreService(context)
}