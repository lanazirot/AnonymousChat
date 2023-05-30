package com.lanazirot.anonymouschat.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.lanazirot.anonymouschat.domain.repositories.AuthRepository
import com.lanazirot.anonymouschat.domain.repositories.ChannelRepository
import com.lanazirot.anonymouschat.domain.repositories.UserRepository
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IAuthAPI
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IChannelAPI
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IUserAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        authAPI: IAuthAPI,
    ) = AuthRepository(
        authAPI = authAPI,
    )

    @Provides
    @Singleton
    fun provideUserRepository(
        userAPI: IUserAPI,
    ) = UserRepository(
        userAPI = userAPI,
    )

    @Provides
    @Singleton
    fun provideChannelRepository(
        channelAPI: IChannelAPI,
    ) = ChannelRepository(
        channelAPI = channelAPI
    )
}