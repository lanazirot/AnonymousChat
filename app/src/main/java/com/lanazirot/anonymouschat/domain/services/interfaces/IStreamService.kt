package com.lanazirot.anonymouschat.domain.services.interfaces

import com.lanazirot.anonymouschat.domain.models.api.CreateChannelDTO
import com.lanazirot.anonymouschat.domain.models.chat.Response
import io.getstream.chat.android.client.models.User

interface IStreamService {
    //UserAPI
    fun connectUser(user : User, lastAttempt: Boolean) : Response<Boolean>
    fun getCurrentUser() : User?
    fun createUser(email: String): Boolean

    //AuthAPI
    fun getAnonymousUser(email : String) : Response<User>

    //ChannelAPI
    fun createChannel(email: String) : Response<Boolean>
    fun addMemberToChannel(channelID: String, email: String) : Response<Boolean>
    fun deleteChannel(channelID: String) : Response<Boolean>
}