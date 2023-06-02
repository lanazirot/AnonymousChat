package com.lanazirot.anonymouschat.di

import com.lanazirot.anonymouschat.domain.constants.APIConstants
import com.lanazirot.anonymouschat.domain.converters.BooleanConverterFactory
import com.lanazirot.anonymouschat.domain.converters.StringConverterFactory
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IAuthAPI
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IChannelAPI
import com.lanazirot.anonymouschat.domain.services.interfaces.api.ISecurityAPI
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IUserAPI
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideAPIBuilder(moshiProvider : Moshi) :Retrofit =
        Builder()
            .baseUrl(APIConstants.BASE_URL)
            .addConverterFactory(StringConverterFactory())
            .addConverterFactory(BooleanConverterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshiProvider))
        .build()

    @Provides
    @Singleton
    fun provideAuthAPI(apiBuilder: Retrofit) :IAuthAPI = apiBuilder.create(IAuthAPI::class.java)

    @Provides
    @Singleton
    fun provideUserAPI(apiBuilder: Retrofit) :IUserAPI = apiBuilder.create(IUserAPI::class.java)

    @Provides
    @Singleton
    fun provideChannelAPI(apiBuilder: Retrofit) :IChannelAPI = apiBuilder.create(IChannelAPI::class.java)

    @Provides
    @Singleton
    fun provideSecurityAPI(apiBuilder: Retrofit) :ISecurityAPI = apiBuilder.create(ISecurityAPI::class.java)
}