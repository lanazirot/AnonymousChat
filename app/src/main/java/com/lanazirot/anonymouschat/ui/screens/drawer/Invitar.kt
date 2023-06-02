package com.lanazirot.anonymouschat.ui.screens.drawer

import android.content.Intent
import androidx.activity.ComponentActivity

fun shareApp(activity: ComponentActivity) {
    val appLink = "https://play.google.com/store/apps/details?id=com.lanazirot.anonymouschat"

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, appLink)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, "Compartir App")
    activity.startActivity(shareIntent)
}