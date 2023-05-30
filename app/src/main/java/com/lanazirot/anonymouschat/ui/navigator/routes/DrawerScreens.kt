package com.lanazirot.anonymouschat.ui.navigator.routes

import com.lanazirot.anonymouschat.MainApp.Companion.context
import com.lanazirot.anonymouschat.R

sealed class DrawerScreens(val title: String, val route: String, val icon: Int)  {
    object Main : DrawerScreens(context.getString(R.string.app_name),"rooms", R.drawable.home)
    object Share : DrawerScreens(context.getString(R.string.share_with_friends),"share",R.drawable.iinvitar)
    object About : DrawerScreens(context.getString(R.string.about_app),"about",R.drawable.idudas)
    object Preferences : DrawerScreens( context.getString(R.string.politics_app),"preferences",R.drawable.ipreferencias)
    object Politics : DrawerScreens(context.getString(R.string.politics_priv),"politics",R.drawable.ipoliticas)
    object Credits : DrawerScreens(context.getString(R.string.credits),"credits",R.drawable.icreditos)
}

val screens = listOf(
    DrawerScreens.Share,
    DrawerScreens.About,
    DrawerScreens.Preferences,
    DrawerScreens.Politics,
    DrawerScreens.Credits,
)