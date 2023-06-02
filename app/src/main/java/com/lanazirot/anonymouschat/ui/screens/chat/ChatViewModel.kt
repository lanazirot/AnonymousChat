package com.lanazirot.anonymouschat.ui.screens.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanazirot.anonymouschat.domain.models.api.location.LatLongDTO
import com.lanazirot.anonymouschat.domain.models.api.location.UserCoordinatesDTO
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IChannelAPI
import com.lanazirot.anonymouschat.domain.services.interfaces.api.ISecurityAPI
import com.lanazirot.anonymouschat.domain.services.interfaces.app.IStreamService
import com.lanazirot.anonymouschat.domain.services.interfaces.location.ILocationClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val locationClient: ILocationClient,
    private val channelRepository: IChannelAPI,
    private val streamService: IStreamService,
    private val securityAPI: ISecurityAPI
) : ViewModel() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _chatState: MutableStateFlow<ChatState> = MutableStateFlow(ChatState())
    val chatState = _chatState

    fun initChat(channelId: String){
        _chatState.value = ChatState(channelId = channelId, alive = true)
        startLocationServices()
    }

    fun reportRoom(){
        val channelId = _chatState.value.channelId
        viewModelScope.launch {
            securityAPI.reportRoom(channelId)
        }.invokeOnCompletion {
            _chatState.value = _chatState.value.copy(reported = true)
        }
    }

    private fun startLocationServices(){
        Log.i("ChatViewModel", "Starting location services")
        val twoAndHalfMinutes = 150000 // 2.5 -> minutes 150000
        locationClient.getLocationUpdates(twoAndHalfMinutes.toLong()).catch {
            //Sacar al usuario de la sala y retornarlo a los chats
            Log.e("ChatViewModel", "Error: ${it.message}")
            _chatState.value = ChatState(alive = false)
        }.onEach {
            Log.i("ChatViewModel", "Location: ${it.latitude} ${it.longitude}")
            val channelId = _chatState.value.channelId
            val latLong = LatLongDTO(it.latitude, it.longitude)
            val userCoordinates = UserCoordinatesDTO(streamService.getCurrentUser()!!.id, latLong, channelId)
            val response = channelRepository.checkIfUserStillInTheRoomByItsCurrentLocation(userCoordinates)
            if(!response){
                //Sacar al usuario de la sala y retornarlo a los chats
                _chatState.value = _chatState.value.copy(alive = false)
            }
        }.launchIn(serviceScope)
    }

}