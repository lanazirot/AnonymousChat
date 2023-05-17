package com.lanazirot.anonymouschat.domain.services.interfaces.api

import com.lanazirot.anonymouschat.domain.models.api.AddMemberToChannelDTO
import com.lanazirot.anonymouschat.domain.models.api.CreateChannelDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface IChannelAPI {
    @POST("channel/")
    suspend fun createChannel(@Body channelDTO: CreateChannelDTO): Response<CreateChannelDTO>

    @POST("channel/add-member-to-channel")
    suspend fun addMemberToChannel(@Body channelDTO: AddMemberToChannelDTO): Response<AddMemberToChannelDTO>

    @DELETE("channel/")
    suspend fun deleteChannel(@Body channelID: String): Response<Boolean>
}