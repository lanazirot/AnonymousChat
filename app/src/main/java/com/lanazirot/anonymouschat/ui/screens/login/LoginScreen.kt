package com.lanazirot.anonymouschat.ui.screens.login

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.getstream.sdk.chat.utils.Utils.locale
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.components.common.StyledText
import com.lanazirot.anonymouschat.ui.components.dialogs.CustomAlertDialog
import com.lanazirot.anonymouschat.ui.navigator.routes.AppScreens
import com.lanazirot.anonymouschat.ui.navigator.routes.DrawerScreens
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.loading.LoadingScreen
import com.lanazirot.anonymouschat.ui.screens.login.states.LoginUIState
import com.lanazirot.anonymouschat.ui.theme.Anonymous

@Composable
fun LoginScreen() {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val navController = GlobalProvider.current.navController
    val uiState by loginViewModel.uiState.collectAsState()

    when (uiState) {
        is LoginUIState.Success -> {
            navController.navigate(DrawerScreens.Main.route)
            loginViewModel.revealNewsChatsForCurrentUser()
        }

        is LoginUIState.Loading -> {
            LoadingScreen()
        }

        else -> {
            LoginData()
        }
    }
}

@Composable
fun LoginData() {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val googleToken = stringResource(id = R.string.google_token)
    val context = LocalContext.current
    val navController = GlobalProvider.current.navController
    val userAux by loginViewModel.userState.collectAsState()

    val openDialog = remember { mutableStateOf(false) }
    val errorMessage by loginViewModel.errorMessage.collectAsState()

    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty())
            openDialog.value = true
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)

            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                loginViewModel.signInWithGoogle(
                    credential = credential
                )
            } catch (e: Exception) {
                Log.d("LoginScreen", e.message ?: "Error al iniciar sesion con Google")
            }
        }

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
                .statusBarsPadding()
                .navigationBarsWithImePadding()
                .verticalScroll(rememberScrollState())
                .height(LocalConfiguration.current.screenHeightDp.dp)
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
                    .testTag("loginImage")
            )
            Spacer(modifier = Modifier.height(32.dp))

            StyledText(
                value = userAux.user.email,
                text = stringResource(R.string.email),
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
                text = stringResource(R.string.password),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {

                    try {
                        loginViewModel.signInWithCredentials()
                    } catch (e: Exception) {
                        Log.d("LoginScreen", e.message ?: "Error al iniciar sesion")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .testTag("loginButton"),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(
                        147, 46, 61
                    )
                )
            ) {
                Text(
                    text = stringResource(R.string.login),
                    color = Color.White,
                    fontFamily = Anonymous,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    try {
                        IniciarSesionConGoogle(googleToken, context, launcher)
                    } catch (e: Exception) {
                        Log.d("LoginScreen", e.message ?: "Error al iniciar sesion con Google")
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = stringResource(R.string.login_google),
                        color = Color.White,
                        fontFamily = Anonymous,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
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
                    text = stringResource(R.string.register),
                    color = Color.White,
                    fontFamily = Anonymous,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            if (openDialog.value) {
                CustomAlertDialog(message = errorMessage) {
                    openDialog.value = false
                    loginViewModel.setError("")
                }
            }
        }
    }
}


fun IniciarSesionConGoogle(
    googleToken: String,
    context: Context,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(googleToken)
        .requestEmail()
        .build()
    val client = GoogleSignIn.getClient(context, options)
    launcher.launch(client.signInIntent)
}