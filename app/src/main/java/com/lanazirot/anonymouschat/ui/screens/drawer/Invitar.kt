package com.lanazirot.anonymouschat.ui.screens.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider

@Composable
fun Invitar() {
    val navController = GlobalProvider.current.navController

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Invitar",
            icon = painterResource(R.drawable.iinvitar),
            buttonIcon = Icons.Filled.ArrowBack
        ) { navController.popBackStack() }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Home Page content here.")
        }
    }
}