package com.lanazirot.anonymouschat.domain.services.interfaces.api

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import javax.inject.Inject

class AuthAPIService @Inject constructor(
    private val apiBulder: Retrofit
){

}