package com.lanazirot.anonymouschat.domain.models.app

sealed class AppScreens(val title: String, val route: String)  {
    object Login : AppScreens("Login","login")
    object Register : AppScreens("Register","register")
}