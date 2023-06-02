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
            val empty_email = composeTestRule.activity.getString(R.string.val_email_empty)
            val empty_password = composeTestRule.activity.getString(R.string.val_pass_empty)
            val empty_confirm_password = composeTestRule.activity.getString(R.string.val_pass_dif)
            val invalid_email = composeTestRule.activity.getString(R.string.val_email_invalid)
            val weak_pass = composeTestRule.activity.getString(R.string.val_pass_weak)
            val email = composeTestRule.activity.getString(R.string.email)
            val password = composeTestRule.activity.getString(R.string.password)
            val confirm_password = composeTestRule.activity.getString(R.string.confirm_password)
            val register = composeTestRule.activity.getString(R.string.register)
            val login = composeTestRule.activity.getString(R.string.login)

            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText(email).assertExists()
            composeTestRule.onNodeWithText(password).assertExists()
            composeTestRule.onNodeWithText(confirm_password).assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText(email).performTextInput("correo")
            composeTestRule.onNodeWithText(password).performTextInput("password1234")
            composeTestRule.onNodeWithText(confirm_password).performTextInput("password1234")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText(invalid_email).assertExists()
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
            val empty_email = composeTestRule.activity.getString(R.string.val_email_empty)
            val empty_password = composeTestRule.activity.getString(R.string.val_pass_empty)
            val empty_confirm_password = composeTestRule.activity.getString(R.string.val_pass_dif)
            val invalid_email = composeTestRule.activity.getString(R.string.val_email_invalid)
            val weak_pass = composeTestRule.activity.getString(R.string.val_pass_weak)
            val email = composeTestRule.activity.getString(R.string.email)
            val password = composeTestRule.activity.getString(R.string.password)
            val confirm_password = composeTestRule.activity.getString(R.string.confirm_password)
            val register = composeTestRule.activity.getString(R.string.register)
            val login = composeTestRule.activity.getString(R.string.login)

            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText(email).assertExists()
            composeTestRule.onNodeWithText(password).assertExists()
            composeTestRule.onNodeWithText(confirm_password).assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText(email).performTextInput("")
            composeTestRule.onNodeWithText(password).performTextInput("password1234")
            composeTestRule.onNodeWithText(confirm_password).performTextInput("password1234")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText(empty_email)
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
            val empty_email = composeTestRule.activity.getString(R.string.val_email_empty)
            val empty_password = composeTestRule.activity.getString(R.string.val_pass_empty)
            val empty_confirm_password = composeTestRule.activity.getString(R.string.val_pass_dif)
            val invalid_email = composeTestRule.activity.getString(R.string.val_email_invalid)
            val weak_pass = composeTestRule.activity.getString(R.string.val_pass_weak)
            val email = composeTestRule.activity.getString(R.string.email)
            val password = composeTestRule.activity.getString(R.string.password)
            val confirm_password = composeTestRule.activity.getString(R.string.confirm_password)
            val register = composeTestRule.activity.getString(R.string.register)
            val login = composeTestRule.activity.getString(R.string.login)

            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText(email).assertExists()
            composeTestRule.onNodeWithText(password).assertExists()
            composeTestRule.onNodeWithText(confirm_password).assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText(email).performTextInput("email@gmail.com")
            composeTestRule.onNodeWithText(password).performTextInput("")
            composeTestRule.onNodeWithText(confirm_password).performTextInput("password1234")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText(empty_password)
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
            val empty_email = composeTestRule.activity.getString(R.string.val_email_empty)
            val empty_password = composeTestRule.activity.getString(R.string.val_pass_empty)
            val empty_confirm_password = composeTestRule.activity.getString(R.string.val_pass_dif)
            val invalid_email = composeTestRule.activity.getString(R.string.val_email_invalid)
            val weak_pass = composeTestRule.activity.getString(R.string.val_pass_weak)
            val email = composeTestRule.activity.getString(R.string.email)
            val password = composeTestRule.activity.getString(R.string.password)
            val confirm_password = composeTestRule.activity.getString(R.string.confirm_password)
            val register = composeTestRule.activity.getString(R.string.register)
            val login = composeTestRule.activity.getString(R.string.login)

            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText(email).assertExists()
            composeTestRule.onNodeWithText(password).assertExists()
            composeTestRule.onNodeWithText(confirm_password).assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText(email).performTextInput("email@gmail.com")
            composeTestRule.onNodeWithText(password).performTextInput("pass")
            composeTestRule.onNodeWithText(confirm_password).performTextInput("pass")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText(weak_pass)
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
            val empty_email = composeTestRule.activity.getString(R.string.val_email_empty)
            val empty_password = composeTestRule.activity.getString(R.string.val_pass_empty)
            val diff_pass = composeTestRule.activity.getString(R.string.val_pass_dif)
            val invalid_email = composeTestRule.activity.getString(R.string.val_email_invalid)
            val weak_pass = composeTestRule.activity.getString(R.string.val_pass_weak)
            val email = composeTestRule.activity.getString(R.string.email)
            val password = composeTestRule.activity.getString(R.string.password)
            val confirm_password = composeTestRule.activity.getString(R.string.confirm_password)
            val register = composeTestRule.activity.getString(R.string.register)
            val login = composeTestRule.activity.getString(R.string.login)

            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText(email).assertExists()
            composeTestRule.onNodeWithText(password).assertExists()
            composeTestRule.onNodeWithText(confirm_password).assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText(email).performTextInput("email@gmail.com")
            composeTestRule.onNodeWithText(password).performTextInput("password1234")
            composeTestRule.onNodeWithText(confirm_password).performTextInput("")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText(diff_pass)
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
            val empty_email = composeTestRule.activity.getString(R.string.val_email_empty)
            val empty_password = composeTestRule.activity.getString(R.string.val_pass_empty)
            val diff_pass = composeTestRule.activity.getString(R.string.val_pass_dif)
            val invalid_email = composeTestRule.activity.getString(R.string.val_email_invalid)
            val weak_pass = composeTestRule.activity.getString(R.string.val_pass_weak)
            val email = composeTestRule.activity.getString(R.string.email)
            val password = composeTestRule.activity.getString(R.string.password)
            val confirm_password = composeTestRule.activity.getString(R.string.confirm_password)
            val register = composeTestRule.activity.getString(R.string.register)
            val login = composeTestRule.activity.getString(R.string.login)

            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText(email).assertExists()
            composeTestRule.onNodeWithText(password).assertExists()
            composeTestRule.onNodeWithText(confirm_password).assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText(email).performTextInput("email@gmail.com")
            composeTestRule.onNodeWithText(password).performTextInput("password1234")
            composeTestRule.onNodeWithText(confirm_password).performTextInput("pass")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText(diff_pass)
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
            val empty_email = composeTestRule.activity.getString(R.string.val_email_empty)
            val empty_password = composeTestRule.activity.getString(R.string.val_pass_empty)
            val diff_pass = composeTestRule.activity.getString(R.string.val_pass_dif)
            val invalid_email = composeTestRule.activity.getString(R.string.val_email_invalid)
            val weak_pass = composeTestRule.activity.getString(R.string.val_pass_weak)
            val email = composeTestRule.activity.getString(R.string.email)
            val password = composeTestRule.activity.getString(R.string.password)
            val confirm_password = composeTestRule.activity.getString(R.string.confirm_password)
            val register = composeTestRule.activity.getString(R.string.register)
            val login = composeTestRule.activity.getString(R.string.login)

            composeTestRule.onNodeWithTag("RegisterScreenLogo").assertExists()
            composeTestRule.onNodeWithText(email).assertExists()
            composeTestRule.onNodeWithText(password).assertExists()
            composeTestRule.onNodeWithText(confirm_password).assertExists()
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").assertExists()

            composeTestRule.onNodeWithText(email).performTextInput("email@gmail.com")
            composeTestRule.onNodeWithText(password).performTextInput("password1234")
            composeTestRule.onNodeWithText(confirm_password).performTextInput("password12345")
            composeTestRule.onNodeWithTag("RegisterScreenRegisterButton").performClick()
            composeTestRule.onNodeWithText(diff_pass)
                .assertExists()
        }
    }
}