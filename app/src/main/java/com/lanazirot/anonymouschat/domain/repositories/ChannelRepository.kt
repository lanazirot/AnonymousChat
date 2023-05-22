package com.lanazirot.anonymouschat.domain.repositories;

import com.lanazirot.anonymouschat.domain.models.api.AddMemberToChannelDTO
import com.lanazirot.anonymouschat.domain.models.api.CreateChannelDTO
import com.lanazirot.anonymouschat.domain.models.api.RandomUserDTO
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IChannelAPI;
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ChannelRepository @Inject constructor(
    private val channelAPI: IChannelAPI
) {
    @OptIn(DelicateCoroutinesApi::class)
    fun createChannel(channelDTO: CreateChannelDTO): Boolean {
        var response :CreateChannelDTO? = null

        runBlocking {
            val job = GlobalScope.launch {
                response = channelAPI.createChannel(channelDTO)
            }

            job.join()
        }

        return response != null
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun addMemberToChannel(channelDTO: AddMemberToChannelDTO): Boolean {
        var response :AddMemberToChannelDTO? = null

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
        var response :Boolean? = null

        runBlocking {
            val job = GlobalScope.launch {
                response = channelAPI.deleteChannel(channelID)
            }

            job.join()
        }

        return response != null
    }
}