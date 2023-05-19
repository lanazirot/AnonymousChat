package com.lanazirot.anonymouschat.domain.services.interfaces.api

import io.getstream.chat.android.client.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IUserAPI {
    @GET("user/connect-user")
    suspend fun generateToken(@Query("email") email: String): Response<String>

    @POST("user/create-user")
    suspend fun createUser(@Query("email") email: String): Response<Unit>
}