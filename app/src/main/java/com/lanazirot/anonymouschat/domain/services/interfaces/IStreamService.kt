package com.lanazirot.anonymouschat.domain.services.interfaces

import com.lanazirot.anonymouschat.domain.models.chat.Response
import io.getstream.chat.android.client.models.User

interface IStreamService {
    fun connectUser(user : User) : Response<Boolean>

    //GET
    fun getAnonymousUser(email : String) : Response<User>

    //POST
    fun createUser() : Response<User>
}