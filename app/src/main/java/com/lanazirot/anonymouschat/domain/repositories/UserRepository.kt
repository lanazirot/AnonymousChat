package com.lanazirot.anonymouschat.domain.repositories

import android.util.Log
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IUserAPI
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userAPI: IUserAPI
) {
    @OptIn(DelicateCoroutinesApi::class)
    fun generateToken(email: String, tokenLocal:String) : String {
        var token = tokenLocal

        runBlocking {
            val job = GlobalScope.launch {
                if (token == "") { //Si no, entonces generamos el token
                    token = userAPI.generateToken(email)

                    if (token != "") {
                        Log.d("StreamService", "Token generated")
                    } else {
                        Log.d("StreamService", "Token not generated")
                    }
                }
            }
            job.join()
        }

        return token
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun createUser(email: String): Boolean {
        var response = ""

        runBlocking {
            val job = GlobalScope.launch {
                response = userAPI.createUser(email)
            }
            job.join()
        }

        return response == ""
    }
}