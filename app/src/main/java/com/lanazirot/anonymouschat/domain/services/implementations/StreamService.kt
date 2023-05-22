package com.lanazirot.anonymouschat.domain.services.implementations

import com.lanazirot.anonymouschat.domain.models.chat.Response
import com.lanazirot.anonymouschat.domain.repositories.AuthRepository
import com.lanazirot.anonymouschat.domain.repositories.UserRepository
import com.lanazirot.anonymouschat.domain.services.interfaces.IAuthenticationService
import com.lanazirot.anonymouschat.domain.services.interfaces.IStreamService
import io.getstream.chat.android.client.models.User
import javax.inject.Inject

class StreamService @Inject constructor(
    private val streamClient : IAuthenticationService,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) : IStreamService {
    override fun connectUser(user: User): Response<Boolean> {
        try {
            var response : Response<Boolean> = Response.Failure(Exception())
                val token = userRepository.generateToken(user.id)

                streamClient.getChatClient().connectUser(
                    user = user,
                    token = token
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
        try {
            val emailForStream = email.replace(".", "-").replace("@", "-")
            val randomUser = authRepository.getRandomUser(emailForStream)

            return Response.Success(User(
                id = emailForStream,
                name = randomUser.username,
                image = randomUser.photoUrl
            ))
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