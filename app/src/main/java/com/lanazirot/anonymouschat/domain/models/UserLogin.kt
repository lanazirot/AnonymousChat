package com.lanazirot.anonymouschat.domain.models

data class UserLogin (
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = ""
)