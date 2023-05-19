package com.lanazirot.anonymouschat.di

import com.lanazirot.anonymouschat.di.AppModule.provideMoshi
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IAuthAPI
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IChannelAPI
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IUserAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideAPIBuilder() :Retrofit = Retrofit.Builder()
        .baseUrl("")
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
        .build()

    @Provides
    @Singleton
    fun provideAuthAPI(retrofit: Retrofit) :IAuthAPI = retrofit.create(IAuthAPI::class.java)

    @Provides
    @Singleton
    fun provideUserAPI(retrofit: Retrofit) :IUserAPI = retrofit.create(IUserAPI::class.java)

    @Provides
    @Singleton
    fun provideChannelAPI(retrofit: Retrofit) :IChannelAPI = retrofit.create(IChannelAPI::class.java)
}