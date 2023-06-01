package com.lanazirot.anonymouschat.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
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
    val navController = GlobalProvider.current.navController
    val loginViewModel: LoginViewModel = hiltViewModel()
    val userAux by loginViewModel.userState.collectAsState()
    val imagePainter = painterResource(R.drawable.user)

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
                .statusBarsPadding()
                .navigationBarsWithImePadding()
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp)
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

<<<<<<< HEAD
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
=======
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
>>>>>>> nuevodiseño

            Spacer(modifier = Modifier.height(16.dp))

<<<<<<< HEAD
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
=======
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
>>>>>>> nuevodiseño

            Spacer(modifier = Modifier.height(16.dp))

<<<<<<< HEAD
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
                    if(userAux.user.email.isEmpty()) {
                        loginViewModel.setError("El correo electrónico no puede estar vacío.")
                        return@Button
                    }
                    if (userAux.user.password.isEmpty()) {
                        loginViewModel.setError("La contraseña no puede estar vacía.")
                        return@Button
                    }
                    if (userAux.user.confirmPassword.isEmpty()) {
                        loginViewModel.setError("La confirmación de la contraseña no puede estar vacía.")
                        return@Button
                    }
                    if(!android.util.Patterns.EMAIL_ADDRESS.matcher(userAux.user.email).matches()) {
                        loginViewModel.setError("El correo electrónico no es válido.")
                        return@Button
                    }
                    if(userAux.user.password.length < 8) {
                        loginViewModel.setError("La contraseña debe tener al menos 8 caracteres.")
                        return@Button
                    }
                    if (userAux.user.confirmPassword.length < 8) {
                        loginViewModel.setError("La confirmación de la contraseña debe tener al menos 8 caracteres.")
                        return@Button
                    }
                    if (validatePasswords(userAux.user.password, userAux.user.confirmPassword)) {
                        try {
                            loginViewModel.createUserWithCredentials()
                        } catch (e: Exception) {
                            loginViewModel.setError(e.message.toString())
                        }
                    } else {
                        loginViewModel.setError("Las contraseñas no coinciden.")
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
                    text = "Registrarse",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { navController.popBackStack() },
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
                    text = "Volver",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (openDialog.value) {
                CustomAlertDialog(message = errorMessage) {
                    openDialog.value = false
                    loginViewModel.setError("")
                }
=======
        StyledText(
            value = userAux.user.confirmPassword,
            onValueChange = {
                loginViewModel.updateUser(
                    userAux.user.copy(confirmPassword = it)
                )
            },
            text = stringResource(R.string.confirm_password),
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
                backgroundColor = Color(
                    147, 46, 61
                )
            )
        ) {
            Text(
                text = stringResource(R.string.register),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.popBackStack() },
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
                text = stringResource(R.string.back),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (openDialog.value) {
            CustomAlertDialog(message = errorMessage) {
                openDialog.value = false
                loginViewModel.setError("")
>>>>>>> nuevodiseño
            }
        }
    }
}

fun validatePasswords(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}