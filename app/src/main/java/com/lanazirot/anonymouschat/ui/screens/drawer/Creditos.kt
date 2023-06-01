package com.lanazirot.anonymouschat.ui.screens.drawer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
            Text(
                text = "Créditos:",
                color = Color.White,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "- Backend -",
                color = Color.White,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Alan Peña Ortiz",
                color = Color.White,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Alan Castro Cruz",
                color = Color.White,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "- Frontend -",
                color = Color.White,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Ezequiel Cantu",
                color = Color.White,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Andrea Martinez",
                color = Color.White,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "- Fullstack -",
                color = Color.White,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Eduardo Jared",
                color = Color.White,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
