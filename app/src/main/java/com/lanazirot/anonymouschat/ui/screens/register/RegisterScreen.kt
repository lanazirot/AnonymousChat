package com.lanazirot.anonymouschat.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.lanazirot.anonymouschat.R
import com.lanazirot.anonymouschat.ui.components.common.StyledText
import com.lanazirot.anonymouschat.ui.components.dialogs.CustomAlertDialog
import com.lanazirot.anonymouschat.ui.navigator.routes.DrawerScreens
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.loading.LoadingScreen
import com.lanazirot.anonymouschat.ui.screens.login.LoginViewModel
import com.lanazirot.anonymouschat.ui.screens.login.states.LoginUIState
import com.lanazirot.anonymouschat.ui.screens.preferences.ThemeViewModel
import com.lanazirot.anonymouschat.ui.theme.Anonymous


@Composable
fun RegisterScreen() {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val navController = GlobalProvider.current.navController

    val uiState by loginViewModel.uiState.collectAsState()

    when (uiState) {
        is LoginUIState.Success -> {
            navController.navigate(DrawerScreens.Main.route)
        }
        is LoginUIState.Loading -> {
            LoadingScreen()
        }
        else -> {
            RegisterData()
        }
    }
}

@Composable
fun RegisterData() {
    val themeViewModel: ThemeViewModel = hiltViewModel()
    val isDarkThemeEnabled by themeViewModel.isDarkThemeEnabled.collectAsState()
    val navController = GlobalProvider.current.navController
    val loginViewModel: LoginViewModel = hiltViewModel()
    val userAux by loginViewModel.userState.collectAsState()

    val openDialog = remember { mutableStateOf(false) }
    val errorMessage by loginViewModel.errorMessage.collectAsState()

    val val1 = stringResource(R.string.val_email_empty)
    val val2 = stringResource(R.string.val_pass_empty)
    val val3 = stringResource(R.string.val_pass_dif)
    val val4 = stringResource(R.string.val_email_invalid)
    val val5 = stringResource(R.string.val_pass_weak)

    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty())
            openDialog.value = true
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
            if(isDarkThemeEnabled){
                Image(
                    painter = painterResource(R.drawable.user),
                    modifier = Modifier.size(200.dp),
                    contentDescription = "Anonymous"
                )
            }
            else{
                Image(
                    painter = painterResource(R.drawable.userclaro),
                    modifier = Modifier.size(200.dp),
                    contentDescription = "Anonymous"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

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
            Spacer(modifier = Modifier.height(16.dp))

            StyledText(
                value = userAux.user.confirmPassword,
                onValueChange = {
                    loginViewModel.updateUser(
                        userAux.user.copy(confirmPassword = it)
                    )
                },
                text = "Confirmar contraseña",
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    if (userAux.user.email.isEmpty()) {
                        loginViewModel.setError(val1)
                        return@Button
                    }
                    if (userAux.user.password.isEmpty()) {
                        loginViewModel.setError(val2)
                        return@Button
                    }
                    if (userAux.user.confirmPassword.isEmpty()) {
                        loginViewModel.setError(val3)
                        return@Button
                    }
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userAux.user.email).matches()) {
                        loginViewModel.setError(val4)
                        return@Button
                    }
                    if (userAux.user.password.length < 8) {
                        loginViewModel.setError(val5)
                        return@Button
                    }
                    if (userAux.user.confirmPassword.length < 8) {
                        loginViewModel.setError(val3)
                        return@Button
                    }
                    if (validatePasswords(userAux.user.password, userAux.user.confirmPassword)) {
                        try {
                            loginViewModel.createUserWithCredentials()
                        } catch (e: Exception) {
                            loginViewModel.setError(e.message.toString())
                        }
                    } else {
                        loginViewModel.setError(val3)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(
                        147, 46, 61
                    )
                )
            ) {
                Text(
                    text = "Registrarse",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = Anonymous,
                    fontWeight = FontWeight.Normal
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(
                        147, 46, 61
                    )
                )
            ) {
                Text(
                    text = "Volver",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = Anonymous,
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
fun validatePasswords(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}