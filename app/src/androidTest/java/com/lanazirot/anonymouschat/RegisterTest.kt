package com.lanazirot.anonymouschat

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lanazirot.anonymouschat.ui.providers.AppProvider
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.register.RegisterScreen
import com.lanazirot.anonymouschat.ui.theme.AnonymousChatTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class RegisterTest {

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_registro_correo_formato_incorrecto() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            val navController = rememberNavController()
            val gp = AppProvider(navController = navController)

            AnonymousChatTheme {
                CompositionLocalProvider(
                    GlobalProvider provides gp
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black
                    ) {
                        RegisterScreen()

                    }
                }
            }
            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText("Correo electrónico").assertExists()
            composeTestRule.onNodeWithText("Contraseña").assertExists()
            composeTestRule.onNodeWithText("Confirmar contraseña").assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText("Correo electrónico").performTextInput("correo")
            composeTestRule.onNodeWithText("Contraseña").performTextInput("password1234")
            composeTestRule.onNodeWithText("Confirmar contraseña").performTextInput("password1234")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText("El correo electrónico no es válido.").assertExists()
        }
    }

    @Test
    fun test_registro_correo_vacio() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            val navController = rememberNavController()
            val gp = AppProvider(navController = navController)

            AnonymousChatTheme {
                CompositionLocalProvider(
                    GlobalProvider provides gp
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black
                    ) {
                        RegisterScreen()

                    }
                }
            }
            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText("Correo electrónico").assertExists()
            composeTestRule.onNodeWithText("Contraseña").assertExists()
            composeTestRule.onNodeWithText("Confirmar contraseña").assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText("Correo electrónico").performTextInput("")
            composeTestRule.onNodeWithText("Contraseña").performTextInput("password1234")
            composeTestRule.onNodeWithText("Confirmar contraseña").performTextInput("password1234")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText("El correo electrónico no puede estar vacío.")
                .assertExists()
        }
    }


    @Test
    fun test_registro_contrasenia_vacio() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            val navController = rememberNavController()
            val gp = AppProvider(navController = navController)

            AnonymousChatTheme {
                CompositionLocalProvider(
                    GlobalProvider provides gp
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black
                    ) {
                        RegisterScreen()

                    }
                }
            }
            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText("Correo electrónico").assertExists()
            composeTestRule.onNodeWithText("Contraseña").assertExists()
            composeTestRule.onNodeWithText("Confirmar contraseña").assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText("Correo electrónico").performTextInput("email@gmail.com")
            composeTestRule.onNodeWithText("Contraseña").performTextInput("")
            composeTestRule.onNodeWithText("Confirmar contraseña").performTextInput("password1234")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText("La contraseña no puede estar vacía.")
                .assertExists()
        }
    }


    @Test
    fun test_registro_contrasenia_menor_8_caracteres() {

        hiltRule.inject()
        composeTestRule.activity.setContent {
            val navController = rememberNavController()
            val gp = AppProvider(navController = navController)

            AnonymousChatTheme {
                CompositionLocalProvider(
                    GlobalProvider provides gp
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black
                    ) {
                        RegisterScreen()

                    }
                }
            }
            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText("Correo electrónico").assertExists()
            composeTestRule.onNodeWithText("Contraseña").assertExists()
            composeTestRule.onNodeWithText("Confirmar contraseña").assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText("Correo electrónico").performTextInput("email@gmail.com")
            composeTestRule.onNodeWithText("Contraseña").performTextInput("pass")
            composeTestRule.onNodeWithText("Confirmar contraseña").performTextInput("pass")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText("La contraseña debe tener al menos 8 caracteres.")
                .assertExists()
        }
    }

    @Test
    fun test_registro_confirmar_contrasenia_vacio() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            val navController = rememberNavController()
            val gp = AppProvider(navController = navController)

            AnonymousChatTheme {
                CompositionLocalProvider(
                    GlobalProvider provides gp
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black
                    ) {
                        RegisterScreen()

                    }
                }
            }
            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText("Correo electrónico").assertExists()
            composeTestRule.onNodeWithText("Contraseña").assertExists()
            composeTestRule.onNodeWithText("Confirmar contraseña").assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText("Correo electrónico").performTextInput("email@gmail.com")
            composeTestRule.onNodeWithText("Contraseña").performTextInput("password1234")
            composeTestRule.onNodeWithText("Confirmar contraseña").performTextInput("")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText("La confirmación de la contraseña no puede estar vacía.")
                .assertExists()
        }
    }

    @Test
    fun test_registro_confirmar_contrasenia_menor_8_caracteres() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            val navController = rememberNavController()
            val gp = AppProvider(navController = navController)

            AnonymousChatTheme {
                CompositionLocalProvider(
                    GlobalProvider provides gp
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black
                    ) {
                        RegisterScreen()

                    }
                }
            }
            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText("Correo electrónico").assertExists()
            composeTestRule.onNodeWithText("Contraseña").assertExists()
            composeTestRule.onNodeWithText("Confirmar contraseña").assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText("Correo electrónico").performTextInput("email@gmail.com")
            composeTestRule.onNodeWithText("Contraseña").performTextInput("password1234")
            composeTestRule.onNodeWithText("Confirmar contraseña").performTextInput("pass")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText("La confirmación de la contraseña debe tener al menos 8 caracteres.")
                .assertExists()
        }
    }

    @Test
    fun test_registro_contrasenias_no_coinciden() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            val navController = rememberNavController()
            val gp = AppProvider(navController = navController)

            AnonymousChatTheme {
                CompositionLocalProvider(
                    GlobalProvider provides gp
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black
                    ) {
                        RegisterScreen()

                    }
                }
            }
            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText("Correo electrónico").assertExists()
            composeTestRule.onNodeWithText("Contraseña").assertExists()
            composeTestRule.onNodeWithText("Confirmar contraseña").assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText("Correo electrónico").performTextInput("email@gmail.com")
            composeTestRule.onNodeWithText("Contraseña").performTextInput("password1234")
            composeTestRule.onNodeWithText("Confirmar contraseña").performTextInput("password12345")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText("Las contraseñas no coinciden.")
                .assertExists()
        }
    }
}