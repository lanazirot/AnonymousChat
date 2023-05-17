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
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiYWxhbi1hYml1ZC1jYXN0cm9AZ21haWwtY29tIn0.y-n8CLexoNpaHpKaFMYLWjD2RrBNYefUAJzq-PZw-Xg"
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
}