package com.lanazirot.anonymouschat.ui.screens.appScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.domain.models.app.StyledText
import com.lanazirot.anonymouschat.domain.models.drawer.DrawerScreens
import com.lanazirot.anonymouschat.ui.screens.login.LoginViewModel

@Composable
fun RegisterScreen(navController : NavController) {
    val loginViewModel: LoginViewModel = hiltViewModel()

    val userAux by loginViewModel.userState.collectAsState()

    val imagePainter = painterResource(R.drawable.user)
    val warning = painterResource(R.drawable.warning)

    val openDialog = remember { mutableStateOf(false)  }
    val errorMessage = remember { mutableStateOf("Algo ha salido mal, intentalo mas tarde.") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = imagePainter,
            modifier = Modifier.size(200.dp),
            contentDescription = "Anonymous"
        )

        Spacer(modifier = Modifier.height(16.dp))

        StyledText(
            value = userAux.user.email,
            text = "Correo electrónico",
            onValueChange = { loginViewModel.updateUser(
                userAux.user.copy(email = it)
            ) },
            visualTransformation = VisualTransformation.None
        )

        Spacer(modifier = Modifier.height(16.dp))

        StyledText(
            value = userAux.user.password,
            onValueChange = { loginViewModel.updateUser(
                userAux.user.copy(password = it)
            ) },
            text = "Contraseña",
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        StyledText(
            value = userAux.user.confirmPassword,
            onValueChange = { loginViewModel.updateUser(
                userAux.user.copy(confirmPassword = it)
            ) },
            text = "Confirmar contraseña",
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (validatePasswords(userAux.user.password, userAux.user.confirmPassword)) {
                    try {
                        loginViewModel.createUserWithCredentials(
                            userAux.user.email,
                            userAux.user.password,
                            { navController.navigate(DrawerScreens.Main.route) }
                        )
                    } catch (e: Exception) {
                        errorMessage.value = e.message.toString()
                        openDialog.value = true
                    }
                } else {
                    errorMessage.value = "Las contraseñas no coinciden."
                    openDialog.value = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(147, 46, 61
            ))
        ) {
            Text(text = "Registrarse", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(147, 46, 61
            ))
        ) {
            Text(text = "Volver", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }


        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Column(Modifier.fillMaxWidth()) {
                        Image(
                            painter = warning,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .size(150.dp),
                            contentDescription = "Warning"
                        )
                    }
                },
                text = {
                    Text(
                        text = errorMessage.value,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h4.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                confirmButton = {
                    Column(Modifier.fillMaxWidth()){
                        Button(
                            onClick = {
                                openDialog.value = false
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)) {
                            Text("De acuerdo")
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

fun validatePasswords(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}