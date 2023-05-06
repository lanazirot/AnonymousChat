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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.domain.models.app.StyledText

@Composable
fun RegisterScreen(navController : NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val imagePainter = painterResource(R.drawable.user)
    val warning = painterResource(R.drawable.warning)

    val openDialog = remember { mutableStateOf(false)  }

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

        StyledText(username,"Username", VisualTransformation.None)

        Spacer(modifier = Modifier.height(16.dp))

        StyledText(password,"Password", PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(16.dp))

        StyledText(confirmPassword,"Confirmar password", VisualTransformation.None)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { openDialog.value = true },
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
                        text = "Algo ha salido mal. Inténtalo de nuevo más tarde",
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