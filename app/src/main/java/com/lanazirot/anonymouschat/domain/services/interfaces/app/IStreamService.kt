package com.lanazirot.anonymouschat.domain.services.interfaces.app

import com.lanazirot.anonymouschat.domain.models.api.channel.ChannelMemberDTO
import com.lanazirot.anonymouschat.domain.models.api.channel.CreateChannelResponseDTO
import com.lanazirot.anonymouschat.domain.models.api.location.LatLongDTO
import com.lanazirot.anonymouschat.domain.models.api.location.UserCoordinatesDTO
import com.lanazirot.anonymouschat.domain.models.chat.Response
import io.getstream.chat.android.client.models.User

interface IStreamService {
    //UserAPI
    fun connectUser(user :User, tokenLocal: String, lastAttempt: Boolean) : Response<String>
    fun getCurrentUser() : User?
    fun createUser(email: String): Boolean

    //AuthAPI
    fun getAnonymousUser(email : String) : Response<User>

    //ChannelAPI
    fun createChannel(email: String, latLongDTO: LatLongDTO) : Response<CreateChannelResponseDTO>
    fun addMemberToChannel(channelID: String, email: String) : Response<Boolean>
    fun deleteChannel(channelID: String) : Response<Boolean>
    fun revealNewsChatsForCurrentUser(channelDTO: ChannelMemberDTO)

    //LocationAPI
    fun checkIfUserStillInTheRoomByItsCurrentLocation(userCoordinates: UserCoordinatesDTO) : Response<Boolean>
}