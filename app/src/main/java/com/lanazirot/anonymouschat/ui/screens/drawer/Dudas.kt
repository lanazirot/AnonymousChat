package com.lanazirot.anonymouschat.ui.screens.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.components.common.ScrollableScreen
import com.lanazirot.anonymouschat.ui.components.common.TopBar
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider

@Composable
fun Dudas() {
    val navController = GlobalProvider.current.navController

    val listItems = listOf(
        stringResource(R.string.general),
        stringResource(R.string.dudes_1),
        stringResource(R.string.dudes_R1),
        stringResource(R.string.dudes_2),
        stringResource(R.string.dudes_R2),
        stringResource(R.string.dudes_3),
        stringResource(R.string.dudes_R3),
        stringResource(R.string.dudes_4),
        stringResource(R.string.dudes_R4)
    )

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primaryVariant), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        TopBar(
            title = stringResource(R.string.dudes),
            icon = painterResource(R.drawable.idudas),
            buttonIcon = Icons.Filled.ArrowBack
        ) { navController.popBackStack() }
        ScrollableScreen(listItems)
    }
}