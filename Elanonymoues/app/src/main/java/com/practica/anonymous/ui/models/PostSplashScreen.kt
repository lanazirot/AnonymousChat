package com.practica.anonymous.ui.models

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.anonymous.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostSplashScreen() {
    //Columna centrada verticalmente
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Imagen de logo
        Image(
            painter = painterResource(id = R.drawable.image), contentDescription = "", modifier =
            Modifier
                .width(150.dp)
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo de texto para el nombre de usuario
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) { Text("Nombre de usuario", style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )) }
            },
            singleLine = true,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(0.90f)

        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para la contraseña
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {


                    //Text with aligned horizontal center
                    Text(
                        text = "Contraseña", style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ))


                        
                }
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para iniciar sesión
        Button(
            onClick = { /* Acción al hacer clic */ },
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para registrarse
        Button(
            onClick = { /*TODO*/ }, modifier = //Modifier con un width del 60%

            Modifier
                .fillMaxWidth(0.90f)
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Registro")
        }
    }
}
