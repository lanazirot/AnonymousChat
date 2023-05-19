package com.lanazirot.anonymouschat.domain.repositories

import com.lanazirot.anonymouschat.domain.services.interfaces.api.IUserAPI;
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class UserRepository @Inject constructor(
    private val userAPI: IUserAPI
){
    fun generateToken(email: String) : Flow<String> = flow {
        val response = userAPI.generateToken(email)
        if (response.isSuccessful) {
            emit(response.body()!!)
        } else {
            throw Exception("Error generating token")
        }
    }.flowOn(Dispatchers.IO)

    fun createUser(email: String) : Flow<Unit> = flow {
        val response = userAPI.createUser(email)
        if (response.isSuccessful) {
            emit(Unit)
        } else {
            throw Exception("Error creating user")
        }
    }.flowOn(Dispatchers.IO)
}