package com.lanazirot.anonymouschat.ui.navigator

sealed class AppScreens(val title: String, val route: String)  {
    object Login : AppScreens("Login","login")
    object Register : AppScreens("Register","register")
    object Rooms : AppScreens("Rooms","rooms")
}