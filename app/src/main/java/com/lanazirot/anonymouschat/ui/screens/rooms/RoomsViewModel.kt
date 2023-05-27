package com.lanazirot.anonymouschat.ui.screens.rooms

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanazirot.anonymouschat.domain.models.chat.Response
import com.lanazirot.anonymouschat.domain.services.implementations.app.LocalStoreService
import com.lanazirot.anonymouschat.domain.services.interfaces.app.ILocalStoreService
import com.lanazirot.anonymouschat.domain.services.interfaces.app.IStreamService
import com.lanazirot.anonymouschat.domain.services.interfaces.location.ILocationClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val streamService: IStreamService,
    private val locationClient: ILocationClient,
) : ViewModel() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    //region Stream
    fun getCurrentUser() = streamService.getCurrentUser()

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