package com.lanazirot.anonymouschat.domain.repositories;

import com.lanazirot.anonymouschat.domain.models.api.AddMemberToChannelDTO
import com.lanazirot.anonymouschat.domain.models.api.CreateChannelDTO
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IChannelAPI;
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ChannelRepository @Inject constructor(
    private val channelAPI: IChannelAPI
) {
    fun createChannel(channelDTO: CreateChannelDTO) : Flow<CreateChannelDTO> = flow {
        val response = channelAPI.createChannel(channelDTO)
        if (response.isSuccessful) {
            emit(response.body()!!)
        } else {
            throw Exception("Error creating channel")
        }
    }.flowOn(Dispatchers.IO)

    fun addMemberToChannel(channelDTO: AddMemberToChannelDTO) : Flow<AddMemberToChannelDTO> = flow {
        val response = channelAPI.addMemberToChannel(channelDTO)
        if (response.isSuccessful) {
            emit(response.body()!!)
        } else {
            throw Exception("Error adding member to channel")
        }
    }.flowOn(Dispatchers.IO)

    fun deleteChannel(channelID: String) : Flow<Boolean> = flow {
        val response = channelAPI.deleteChannel(channelID)
        if (response.isSuccessful) {
            emit(response.body()!!)
        } else {
            throw Exception("Error deleting channel")
        }
    }.flowOn(Dispatchers.IO)
}