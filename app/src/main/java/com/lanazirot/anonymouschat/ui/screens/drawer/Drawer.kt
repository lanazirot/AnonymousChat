package com.lanazirot.anonymouschat.ui.screens.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.navigator.routes.AppScreens
import com.lanazirot.anonymouschat.ui.navigator.routes.screens
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.login.LoginViewModel

@Composable
fun Drawer (
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit,
    onCloseDrawer: () -> Unit
) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val name = Firebase.auth.currentUser?.displayName ?: ""
    val navController = GlobalProvider.current.navController

    Column(
        modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(Color(83, 83, 83))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.Black).padding(start = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.iusuario),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 16.dp)
            )
            Text(
                text = name,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        screens.forEach { screen ->
            Row(
                modifier = Modifier.padding(start = 25.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(screen.icon),
                    contentDescription = "null",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = screen.title,
                    color = Color.White,
                    fontSize = 21.sp,
                    modifier = Modifier.clickable {
                        onDestinationClicked(screen.route)
                    }
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
        }
        Button(
            onClick = {
                onCloseDrawer()
                loginViewModel.logout()
                navController.navigate(AppScreens.Login.route) {
                    popUpTo(AppScreens.Login.route) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier.padding(8.dp),
            content = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.isalir),
                        contentDescription = stringResource(R.string.logout),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(R.string.logout), color = Color.White)
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
        )
    }
}