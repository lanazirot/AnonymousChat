package com.lanazirot.anonymouschat.ui.navigator.routes

import com.lanazirot.anonymouschat.MainApp.Companion.context
import com.lanazirot.anonymouschat.R

sealed class AppScreens(val title: String, val route: String)  {
    object Login : AppScreens(context.getString(R.string.login),"login")
    object Register : AppScreens(context.getString(R.string.register),"register")
    object Chat: AppScreens(context.getString(R.string.chat),"chat")
}