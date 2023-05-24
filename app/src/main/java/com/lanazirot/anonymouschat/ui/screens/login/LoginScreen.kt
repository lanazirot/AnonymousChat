package com.lanazirot.anonymouschat.ui.screens.login

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.domain.models.app.StyledText
import com.lanazirot.anonymouschat.ui.navigator.routes.AppScreens
import com.lanazirot.anonymouschat.ui.navigator.routes.DrawerScreens
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.theme.Anonymous

@Composable
fun LoginScreen() {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val googleToken = stringResource(id = R.string.google_token)
    val context = androidx.compose.ui.platform.LocalContext.current
    val navController = GlobalProvider.current.navController
    val userAux by loginViewModel.userState.collectAsState()

    //variable by remember of a string
    val excepteder = remember { mutableStateOf("") }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)

            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential =
                    com.google.firebase.auth.GoogleAuthProvider.getCredential(account.idToken, null)
                loginViewModel.signInWithGoogle(
                    credential = credential,
                    toHome = {
                        navController.navigate(DrawerScreens.Main.route)
                    }
                )
            } catch (ignore: ApiException) {
                Log.d("LoginScreen", ignore.toString())
            }
        }







    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (loginViewModel.flagErrorOnCard.collectAsState().value){
            AlertDialog(
                onDismissRequest = {
                    loginViewModel.updateFlagErrorOnCard(false)
                    excepteder.value = ""
                },
                text = {
                       Text(text=excepteder.value.toString(),color = Color.Red,fontSize = 20.sp,fontWeight = FontWeight.Bold)
                },
                title = {
                    Text(text = "Error",color = Color.Red,fontSize = 20.sp,fontWeight = FontWeight.Bold)
                },
                confirmButton = {
                    Button(
                        onClick = {
                            loginViewModel.updateFlagErrorOnCard(false)
                            excepteder.value = ""
                        }
                    ) {
                        Text(text = "Aceptar")
                    }
                }
                )
        }

        //Imagen de logo
        Image(
            painter = painterResource(id = R.drawable.login), contentDescription = "", modifier =
            Modifier
                .width(150.dp)
                .height(150.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))

        StyledText(
            value = userAux.user.email,
            text = "Correo electrónico",
            onValueChange = {
                loginViewModel.updateUser(
                    userAux.user.copy(email = it)
                )
            },
            visualTransformation = VisualTransformation.None
        )

        Spacer(modifier = Modifier.height(16.dp))

        StyledText(
            value = userAux.user.password,
            onValueChange = {
                loginViewModel.updateUser(
                    userAux.user.copy(password = it)
                )
            },
            text = "Contraseña",
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                try {
                    if (userAux.user.email.isBlank() || userAux.user.email.isNullOrEmpty()) {
                        throw Exception("El correo electrónico no puede estar vacío")
                    }
                    if (userAux.user.password.isBlank() || userAux.user.password.isNullOrEmpty()) {
                        throw Exception("La contraseña no puede estar vacía")
                    }
                } catch (e: Exception) {
                    Log.d("LoginScreen", e.toString())
                    Log.d("FlagError", loginViewModel.isFlagOn().toString())
                    excepteder.value = e.toString()
                    loginViewModel.updateFlagErrorOnCard(true)
                    Log.d("FlagError", loginViewModel.isFlagOn().toString())

                }

            try {
                loginViewModel.signInWithCredentials(
                    toHome = { navController.navigate("${DrawerScreens.Main.route}/${userAux.user.email}") },
                )
            } catch (e: Exception) {
                Log.d("LoginScreen", e.toString())
                Log.d("FlagError", loginViewModel.isFlagOn().toString())
                excepteder.value = e.toString()
                loginViewModel.updateFlagErrorOnCard(true)
                Log.d("FlagError", loginViewModel.isFlagOn().toString())
            }
    },
    modifier = Modifier
        .fillMaxWidth()
        .height(45.dp),
    colors = ButtonDefaults.buttonColors(
        backgroundColor = Color(
            147, 46, 61
        )
    )
    ) {
        Text(
            text = "Iniciar sesión",
            color = Color.White,
            fontFamily = Anonymous,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = {
            val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(googleToken)
                .requestEmail()
                .build()
            val client = GoogleSignIn.getClient(context, options)
            launcher.launch(client.signInIntent)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(
                147, 46, 61
            )
        )
    ) {
        Text(
            text = "Iniciar sesión con Google",
            color = Color.White,
            fontFamily = Anonymous,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = { navController.navigate(AppScreens.Register.route) },
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(
                147, 46, 61
            )
        )
    ) {
        Text(
            text = "Registrarme",
            color = Color.White,
            fontFamily = Anonymous,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }
}
}

