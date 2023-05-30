package com.lanazirot.anonymouschat.ui.screens.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.components.common.TopBar
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider

@Composable
fun Creditos() {
    val navController = GlobalProvider.current.navController

    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        TopBar(
            title = stringResource(R.string.credits),

            icon = painterResource(R.drawable.icreditos),
            buttonIcon = Icons.Filled.ArrowBack
        ) { navController.popBackStack() }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.home_page_content))
        }
    }
}