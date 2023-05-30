package com.lanazirot.anonymouschat.domain.models.api

import com.lanazirot.anonymouschat.domain.models.api.location.LatLongDTO

data class CreateChannelDTO (
    var email: String = "",
    var coords: LatLongDTO = LatLongDTO(0.0,0.0)
)
