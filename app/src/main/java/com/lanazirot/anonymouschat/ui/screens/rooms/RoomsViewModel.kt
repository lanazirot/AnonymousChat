package com.lanazirot.anonymouschat.ui.screens.rooms

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.lanazirot.anonymouschat.domain.models.api.channel.ChannelMemberDTO
import com.lanazirot.anonymouschat.domain.models.api.location.LatLongDTO
import com.lanazirot.anonymouschat.domain.services.interfaces.app.IStreamService
import com.lanazirot.anonymouschat.domain.services.interfaces.location.ILocationClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val streamService: IStreamService,
    private val locationClient: ILocationClient,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : ViewModel() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    //region Stream
    fun getCurrentUser() = streamService.getCurrentUser()

    @SuppressLint("MissingPermission")
    fun createRoom() {
        fusedLocationProviderClient.lastLocation.addOnCompleteListener{
            if (it.isSuccessful) {
                val location = it.result
                if (location != null) {
                    Log.i("StreamService", "Location: ${location.latitude} ${location.longitude}")
                    val latLong = LatLongDTO(location.latitude, location.longitude)
                    val tryCreateChannel = streamService.createChannel(getCurrentUser()!!.id, latLong)
                    Log.i("StreamService", "Channel created: $tryCreateChannel")
                }
            }
        }
    }

    fun revealNewsChatsForCurrentUser() {
        val twoAndHalfMinutes = 30000
        locationClient.getLocationUpdates(twoAndHalfMinutes.toLong()).catch {
        }.onEach {

            /*
            * Entry point to check data.
            *
            * Here, every 30 seconds, we will check if the user has new room available.
            *
            * This is the most important part of the app, so we need to be careful with this. REALLY CAREFUL.
            *
            * */

            val latLong = LatLongDTO(it.latitude, it.longitude)
            val channelDTO = getCurrentUser()?.let { it1 -> ChannelMemberDTO(it1.id, latLong) }
            if(channelDTO != null)
                streamService.revealNewsChatsForCurrentUser(channelDTO)

        }.launchIn(serviceScope)
    }
    //endregion

    //region Location
//    fun startLocationServices() {
//        val twoAndHalfMinutes = 10000 // 2.5 -> minutes 150000
//        Log.d("LocationClient", "Starting location services")
//        locationClient.getLocationUpdates(twoAndHalfMinutes.toLong()).catch {
//            Log.e("LocationClient", "Error: ${it.message}")
//        }.onEach {
//            Log.d("LocationClient", "Location: ${it.latitude} ${it.longitude}")
//
//            /*
//            * Entry point to check data.
//            *
//            * Here, every 2.5 minutes, we will check if the user is in a room.
//            *
//            * This is the most important part of the app, so we need to be careful with this. REALLY CAREFUL.
//            *
//            * */
//
//        }.launchIn(serviceScope)
//    }
    //endregion
}