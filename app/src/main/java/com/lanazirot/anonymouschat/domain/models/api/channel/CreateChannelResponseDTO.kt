package com.lanazirot.anonymouschat.domain.models.api.channel

data class CreateChannelResponseDTO(
    val createdBy: String,
    val randomName: String,
    val createdAt: String,
    val cid: String
)