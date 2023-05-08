package com.lanazirot.anonymouschat.domain.models.drawer

import com.lanazirot.anonymouschat.R

sealed class DrawerScreens(val title: String, val route: String, val icon: Int)  {
    object Main : DrawerScreens("Anonymous Chat","rooms", R.drawable.home)
    object Share : DrawerScreens("Invitar amigos","share",R.drawable.iinvitar)
    object About : DrawerScreens("Acerca de la aplicacion","about",R.drawable.idudas)
    object Preferences : DrawerScreens( "Preferencias de la aplicacion","preferences",R.drawable.ipreferencias)
    object Politics : DrawerScreens("Politicas de privacidad","politics",R.drawable.ipoliticas)
    object Credits : DrawerScreens("Creditos","credits",R.drawable.icreditos)
}

val screens = listOf(
    DrawerScreens.Main,
    DrawerScreens.Share,
    DrawerScreens.About,
    DrawerScreens.Preferences,
    DrawerScreens.Politics,
    DrawerScreens.Credits,
)