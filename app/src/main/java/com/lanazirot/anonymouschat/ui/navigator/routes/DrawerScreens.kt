package com.lanazirot.anonymouschat.ui.navigator.routes

import androidx.compose.ui.res.stringResource
import com.lanazirot.anonymouschat.MainApp.Companion.context
import com.lanazirot.anonymouschat.R

sealed class DrawerScreens(val title: Int, val route: String, val icon: Int)  {
    object Main : DrawerScreens(R.string.app_name,"rooms", R.drawable.home)
    object About : DrawerScreens(R.string.about_app,"about",R.drawable.idudas)
    object Preferences : DrawerScreens( R.string.preferences_app,"preferences",R.drawable.ipreferencias)
    object Politics : DrawerScreens(R.string.politics_priv,"politics",R.drawable.ipoliticas)
    object Credits : DrawerScreens(R.string.credits,"credits",R.drawable.icreditos)
}

val screens = listOf(
    DrawerScreens.About,
    DrawerScreens.Preferences,
    DrawerScreens.Politics,
    DrawerScreens.Credits,
)