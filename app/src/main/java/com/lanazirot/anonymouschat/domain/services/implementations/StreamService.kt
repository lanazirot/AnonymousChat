package com.lanazirot.anonymouschat.domain.services.implementations

import com.lanazirot.anonymouschat.domain.models.chat.Response
import com.lanazirot.anonymouschat.domain.services.interfaces.IAuthenticationService
import com.lanazirot.anonymouschat.domain.services.interfaces.IStreamService
import io.getstream.chat.android.client.models.User
import javax.inject.Inject

class StreamService @Inject constructor(
    private val streamClient : IAuthenticationService
) : IStreamService {
    override fun connectUser(user: User): Response<Boolean> {
        try {
            var response : Response<Boolean> = Response.Failure(Exception())
            var currentToken = ""

            streamClient.getChatClient().connectUser(
                user = user,
                //TODO: Token generado por API
                token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiYWxhbi1zc3VjMjMxMUBnbWFpbC1jb20iLCJleHAiOjE2ODQ0OTc2OTd9.2FyPo83BSWpDaf6zJtgm5Jc-pG1Xx0DQi8nnla4YNQI"
            ).enqueue { result ->
                if (result.isSuccess) {
                    response = Response.Success(true)
                } else {
                    response = Response.Failure(Exception())
                }
            }

            return response
        } catch (e: Exception) {
            return Response.Failure(e)
        }
    }

    override fun getAnonymousUser(email : String): Response<User> {
        val emailForStream = email.replace(".", "-")

        try {
            val user = User(
                id = emailForStream,
                name = "alanc", //TODO: Nombre aleatorio generado por API
                image = "https://robohash.org/alanc" //TODO: Imagen aleatoria generada por API
            )
            return Response.Success(user)
        } catch (e: Exception) {
            return Response.Failure(e)
        }
    }

    override fun createUser(): Response<User> {
        TODO()
    }

    override fun getCurrentUser() : User? {
        return streamClient.getChatClient().getCurrentUser()
    }
}