package com.lanazirot.anonymouschat.domain.services.interfaces.api

import com.lanazirot.anonymouschat.domain.constants.APIConstants
import retrofit2.http.*

interface IUserAPI {
    @GET("user/connect-user")
    @Headers("X-API-Key: ${APIConstants.API_KEY}")
    suspend fun generateToken(
        @Query("email") email: String
    ): String

    @POST("user/create-user")
    @Headers("X-API-Key: ${APIConstants.API_KEY}")
    suspend fun createUser(
        @Query("email") email: String
    ): String
}