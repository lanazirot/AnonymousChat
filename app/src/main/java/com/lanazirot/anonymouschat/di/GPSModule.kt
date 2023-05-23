package com.lanazirot.anonymouschat.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.lanazirot.anonymouschat.domain.services.implementations.location.LocationClient
import com.lanazirot.anonymouschat.domain.services.interfaces.location.ILocationClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GPSModule {

    @Provides
    @Singleton
    fun provideLocationClient(@ApplicationContext context: Context): ILocationClient = LocationClient(
        context, LocationServices.getFusedLocationProviderClient(context)
    )
}