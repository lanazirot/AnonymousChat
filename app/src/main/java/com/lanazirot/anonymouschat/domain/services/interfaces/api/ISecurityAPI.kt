package com.lanazirot.anonymouschat.domain.services.interfaces.api

import retrofit2.http.Body
import retrofit2.http.POST

interface ISecurityAPI {
    @POST("security/report-room")
    suspend fun reportRoom(@Body RoomId: String)
}