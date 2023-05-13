package com.lanazirot.anonymouschat.domain.services.implementations

import com.lanazirot.anonymouschat.domain.models.Response
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

            streamClient.provideStreamClient().connectUser(
                user = user,
                //TODO: Token generado por API
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiYWxhbi1zc3VjMjMxMUBnbWFpbC1jb20ifQ.wCCUpRnCQjngni_uikul7rKMlF3WxDN-keZKP5lZ2BM"
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

    override fun getUser(email : String): Response<User> {
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
}