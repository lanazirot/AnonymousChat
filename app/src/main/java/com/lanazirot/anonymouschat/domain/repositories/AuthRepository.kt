package com.lanazirot.anonymouschat.domain.repositories

import com.lanazirot.anonymouschat.domain.models.api.RandomUserDTO
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IAuthAPI
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authAPI: IAuthAPI,
) {
    @OptIn(DelicateCoroutinesApi::class)
    fun getRandomUser(email: String): RandomUserDTO {
        var randomUser = RandomUserDTO("", "")

        runBlocking {
            val job = GlobalScope.launch {
                randomUser = authAPI.getRandomUser(email)
            }

            job.join()
        }

        return randomUser
    }
}