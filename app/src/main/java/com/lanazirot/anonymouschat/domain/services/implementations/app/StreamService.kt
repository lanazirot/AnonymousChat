package com.lanazirot.anonymouschat.domain.services.implementations.app

import com.lanazirot.anonymouschat.domain.models.api.AddMemberToChannelDTO
import com.lanazirot.anonymouschat.domain.models.api.CreateChannelDTO
import com.lanazirot.anonymouschat.domain.models.api.channel.ChannelMemberDTO
import com.lanazirot.anonymouschat.domain.models.api.channel.CreateChannelResponseDTO
import com.lanazirot.anonymouschat.domain.models.api.location.LatLongDTO
import com.lanazirot.anonymouschat.domain.models.api.location.UserCoordinatesDTO
import com.lanazirot.anonymouschat.domain.models.chat.Response
import com.lanazirot.anonymouschat.domain.repositories.AuthRepository
import com.lanazirot.anonymouschat.domain.repositories.ChannelRepository
import com.lanazirot.anonymouschat.domain.repositories.UserRepository
import com.lanazirot.anonymouschat.domain.services.interfaces.app.IAuthenticationService
import com.lanazirot.anonymouschat.domain.services.interfaces.app.IStreamService
import io.getstream.chat.android.client.models.User
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class StreamService @Inject constructor(
    private val streamClient : IAuthenticationService,
    private val userRepository: UserRepository,
    private val channelRepository: ChannelRepository,
    private val authRepository: AuthRepository
) : IStreamService {
    override fun getCurrentUser(): User? {
        return streamClient.getChatClient().getCurrentUser()
    }

    override fun connectUser(user: User, tokenLocal:String, lastAttempt: Boolean): Response<String> {
        try {
            var response: Response<String> = Response.Failure(Exception())
            val token = userRepository.generateToken(user.id, tokenLocal)

            streamClient.getChatClient().connectUser(
                user = user,
                token = token
            ).enqueue { result ->
                if (result.isSuccess) { //Si te lograste conectar, adelante..
                    response = Response.Success(token) //Regresa el token para guardarlo localmente
                } else { //Si no lo lograste, es porque el usuario aun no existe en Stream, entonces lo creamos y volvemos a intentar
                    if (!lastAttempt) { //Siempre y cuando no haya intentado crearlo anteriormente
                        if (createUser(user.id)) //Si logro crear el usuario, volvemos a intentar
                            runBlocking {
                                response = connectUser(
                                    user = user,
                                    tokenLocal = token,
                                    lastAttempt = true
                                ) //Intentamos de nuevo y regresamos la respuesta
                            }
                    } //Si no logro crear el usuario, regresamos la respuesta erronea
                }
            }

            return response
        } catch (e: Exception) {
            return Response.Failure(e)
        }
    }

    override fun getAnonymousUser(email: String): Response<User> {
        return try {
            val emailForStream = email.replace(".", "-").replace("@", "-")
            val randomUser = authRepository.getRandomUser(emailForStream)

            Response.Success(
                User(
                    id = emailForStream,
                    name = randomUser.username,
                    image = randomUser.photoUrl
                )
            )
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun createUser(email: String): Boolean {
        return try {
            val emailForStream = email.replace(".", "-").replace("@", "-")
            userRepository.createUser(emailForStream)
        } catch (e: Exception) {
            false
        }
    }

    override fun createChannel(email: String, latLongDTO: LatLongDTO): Response<CreateChannelResponseDTO> {
        return try {
            val response = channelRepository.createChannel(
                CreateChannelDTO(
                    email = email,
                    coords = latLongDTO
                )
            )

            Response.Success(response)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun deleteChannel(channelID: String): Response<Boolean> {
        return try {
            val response = channelRepository.deleteChannel(channelID)

            if (response) {
                Response.Success(true)
            } else {
                Response.Failure(Exception())
            }
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun checkIfUserStillInTheRoomByItsCurrentLocation(
        userCoordinates: UserCoordinatesDTO,
    ): Response<Boolean> {
        return try {
            val response = channelRepository.checkIfUserStillInTheRoomByItsCurrentLocation(
                userCoordinatesDTO = userCoordinates
            )
            if (response) Response.Success(true) else Response.Failure(Exception())
            Response.Success(true)
        }catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun addMemberToChannel(channelID: String, email: String): Response<Boolean> {
        try {
            val response = channelRepository.addMemberToChannel(
                AddMemberToChannelDTO(
                    channelID = channelID,
                    email = email
                )
            )

            return if (response) {
                Response.Success(true)
            } else {
                Response.Failure(Exception())
            }
        } catch (e: Exception) {
            return Response.Failure(e)
        }
    }

    override fun revealNewsChatsForCurrentUser(channelDTO: ChannelMemberDTO) {
        try {
            channelRepository.revealNewsChatsForCurrentUser(channelDTO)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}