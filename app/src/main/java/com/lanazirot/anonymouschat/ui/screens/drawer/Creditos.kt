package com.lanazirot.anonymouschat.ui.screens.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.components.common.TopBar
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider

@Composable
fun Creditos() {
    val navController = GlobalProvider.current.navController

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primaryVariant)) {
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
            Text(
                text = "- Backend -",
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(20.dp)
            )
            Text(
                text = "Alan Peña Ortiz",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(18.dp)
            )
            Text(
                text = "Alan Castro Cruz",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(18.dp)
            )
            Text(
                text = "- Frontend -",
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(20.dp)
            )
            Text(
                text = "Ezequiel Cantu",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(18.dp)
            )
            Text(
                text = "Andrea Martinez",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(18.dp)
            )
            Text(
                text = "- Testing -",
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(20.dp)
            )
            Text(
                text = "Eduardo Jared",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(18.dp)
            )
        }
    }
}
