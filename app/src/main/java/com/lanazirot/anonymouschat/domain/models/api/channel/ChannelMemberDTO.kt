package com.lanazirot.anonymouschat.domain.models.api.channel

import com.lanazirot.anonymouschat.domain.models.api.location.LatLongDTO

data class ChannelMemberDTO (
    val email:String,
    val currentCoords: LatLongDTO
)