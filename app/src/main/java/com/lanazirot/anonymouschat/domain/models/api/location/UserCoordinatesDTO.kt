package com.lanazirot.anonymouschat.domain.models.api.location

data class UserCoordinatesDTO(
    val email: String,
    val currentCoords : LatLongDTO,
    val channelID: String
)