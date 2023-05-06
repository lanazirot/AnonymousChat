package com.lanazirot.anonymouschat.domain.models.drawer

import com.lanazirot.anonymouschat.R

sealed class DrawerScreens(val title: String, val route: String, val icon: Int)  {
    object PantallaP : DrawerScreens("Anonymous Chat","anonymous", R.drawable.home)
    object Invitar : DrawerScreens("Invitar amigos","invitar",R.drawable.iinvitar)
    object Dudas : DrawerScreens("Dudas sobre la app","dudas",R.drawable.idudas)
    object Preferencias : DrawerScreens( "Preferencias de la app","preferencias",R.drawable.ipreferencias)
    object Politicas : DrawerScreens("Politicas de privacidad","politicas",R.drawable.ipoliticas)
    object Creditos : DrawerScreens("Creditos","creditos",R.drawable.icreditos)
}

val screens = listOf(
    DrawerScreens.PantallaP,
    DrawerScreens.Invitar,
    DrawerScreens.Dudas,
    DrawerScreens.Preferencias,
    DrawerScreens.Politicas,
    DrawerScreens.Creditos,
)