package com.lanazirot.anonymouschat.ui.screens.rooms

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.lanazirot.anonymouschat.domain.models.api.location.LatLongDTO
import com.lanazirot.anonymouschat.domain.services.interfaces.app.IStreamService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val streamService: IStreamService,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : ViewModel() {
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
    //endregion
}