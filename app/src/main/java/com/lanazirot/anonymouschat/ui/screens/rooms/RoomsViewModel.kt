package com.lanazirot.anonymouschat.ui.screens.rooms

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanazirot.anonymouschat.domain.models.chat.Response
import com.lanazirot.anonymouschat.domain.services.interfaces.IStreamService
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
    private val locationClient: ILocationClient
) : ViewModel() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    //region Users
    fun getCurrentUser() = streamService.getCurrentUser()
    fun connectUser(email: String) {
        val userResponse = streamService.getAnonymousUser(email)
        if (userResponse is Response.Success) {
            val user = userResponse.data
            if (user != null) {
                val connectionResponse = streamService.connectUser(user, false)
                if (connectionResponse is Response.Success) {
                    Log.d("StreamService", "User connected")
                } else {
                    Log.d("StreamService", "User not connected")
                }
            }
        }
    }

    fun createRoom() {
        streamService.createChannel(getCurrentUser()!!.id)
    }
    //endregion

    //region Location
    fun startLocationServices() {
        val fiveMinutes = 1000 //Reminder to update this to actually 5 minutes when done testing
        Log.d("LocationClient", "Starting location services")
        locationClient.getLocationUpdates(fiveMinutes.toLong()).catch {
            Log.e("LocationClient", "Error: ${it.message}")
        }.onEach {
            Log.d("LocationClient", "Location: ${it.latitude} ${it.longitude}")
        }.launchIn(serviceScope)
    }
    //endregion
}