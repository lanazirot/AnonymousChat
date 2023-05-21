package com.lanazirot.anonymouschat.domain.models.chat

data class UserLogin (
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = ""
)