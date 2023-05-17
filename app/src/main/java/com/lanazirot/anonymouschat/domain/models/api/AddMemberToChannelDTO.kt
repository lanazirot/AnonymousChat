package com.lanazirot.anonymouschat.domain.models.api

data class AddMemberToChannelDTO (
    var channelID: String = "",
    var email: String = "",
)