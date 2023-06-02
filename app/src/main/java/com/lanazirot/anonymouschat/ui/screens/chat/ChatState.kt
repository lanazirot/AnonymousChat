package com.lanazirot.anonymouschat.ui.screens.chat

data class ChatState(
    val channelId : String = "",
    val alive: Boolean = true,
    val reported : Boolean = false
)