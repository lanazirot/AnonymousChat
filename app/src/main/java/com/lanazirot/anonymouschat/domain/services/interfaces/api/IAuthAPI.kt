package com.lanazirot.anonymouschat.domain.services.interfaces.api

import com.lanazirot.anonymouschat.domain.models.api.RandomUserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IAuthAPI {
    @GET("auth/anonymous-login")
    suspend fun getRandomUser(@Query("email") email: String): Response<RandomUserDTO>
}