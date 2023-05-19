package com.lanazirot.anonymouschat.domain.repositories

import com.lanazirot.anonymouschat.domain.models.api.RandomUserDTO
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IAuthAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authAPI: IAuthAPI,
){
    fun getRandomUser(email: String) : Flow<RandomUserDTO> = flow {
        val response = authAPI.getRandomUser(email)
        if (response.isSuccessful) {
            emit(response.body()!!)
        } else {
            throw Exception("Error getting random user")
        }
    }.flowOn(Dispatchers.IO)}