package com.lanazirot.anonymouschat.ui.screens.drawer

import android.content.Intent
import androidx.activity.ComponentActivity
import com.lanazirot.anonymouschat.MainApp.Companion.context
import com.lanazirot.anonymouschat.R

fun shareApp(activity: ComponentActivity) {
    val appLink = "https://play.google.com/store/apps/details?id=com.lanazirot.anonymouschat"

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, appLink)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, context.getString(R.string.share_app))
    activity.startActivity(shareIntent)
}