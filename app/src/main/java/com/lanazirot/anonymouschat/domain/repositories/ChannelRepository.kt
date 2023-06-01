package com.lanazirot.anonymouschat.domain.repositories;

import com.lanazirot.anonymouschat.domain.models.api.AddMemberToChannelDTO
import com.lanazirot.anonymouschat.domain.models.api.CreateChannelDTO
import com.lanazirot.anonymouschat.domain.models.api.channel.ChannelMemberDTO
import com.lanazirot.anonymouschat.domain.models.api.channel.CreateChannelResponseDTO
import com.lanazirot.anonymouschat.domain.models.api.location.UserCoordinatesDTO
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IChannelAPI
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChannelRepository @Inject constructor(
    private val channelAPI: IChannelAPI,
) {
    @OptIn(DelicateCoroutinesApi::class)
    fun createChannel(channelDTO: CreateChannelDTO): CreateChannelResponseDTO {
        var response: CreateChannelResponseDTO? = null

        runBlocking {
            val job = GlobalScope.launch {
                response = channelAPI.createChannel(channelDTO)
            }

            job.join()
        }

        return response!!
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun addMemberToChannel(channelDTO: AddMemberToChannelDTO): Boolean {
        var response: AddMemberToChannelDTO? = null

        runBlocking {
            val job = GlobalScope.launch {
                response = channelAPI.addMemberToChannel(channelDTO)
            }

            job.join()
        }

        return response != null
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteChannel(channelID: String): Boolean {
        var response: Boolean? = null

        runBlocking {
            val job = GlobalScope.launch {
                response = channelAPI.deleteChannel(channelID)
            }

            job.join()
        }

        return response != null
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun checkIfUserStillInTheRoomByItsCurrentLocation(
        userCoordinatesDTO: UserCoordinatesDTO,
    ): Boolean {
        var response: Boolean? = null
        runBlocking {
            val job = GlobalScope.launch {
                response = channelAPI.checkIfUserStillInTheRoomByItsCurrentLocation(
                    userCoordinatesDTO
                )
            }

            job.join()
        }

        return response != null
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun revealNewsChatsForCurrentUser(channelDTO: ChannelMemberDTO)  {
        runBlocking {
            val job = GlobalScope.launch {
                channelAPI.revealNewsChatsForCurrentUser(channelDTO)
            }

            job.join()
        }
    }
}