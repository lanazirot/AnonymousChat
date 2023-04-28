package com.lanazirot.anonymouschat.ui.screens.appScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.domain.models.app.StyledText

@Composable
fun PostSplashScreen(navController: NavController) {
    //Columna centrada verticalmente
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Imagen de logo
        Image(
            painter = painterResource(id = R.drawable.login), contentDescription = "", modifier =
            Modifier
                .width(150.dp)
                .height(150.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))

        StyledText(variable = "", text = "Nombre de usuario", visualTransformation = VisualTransformation.None)

        Spacer(modifier = Modifier.height(16.dp))

        StyledText(variable = "", text = "Contraseña", visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("anonymous") },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(147, 46, 61
            ))
        ) {
            Text(text = "Iniciar sesión", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(147, 46, 61
            ))
        ) {
            Text(text = "Registro", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}