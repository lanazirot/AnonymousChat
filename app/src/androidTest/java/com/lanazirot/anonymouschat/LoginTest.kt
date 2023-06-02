package com.lanazirot.anonymouschat

import android.Manifest
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.IntSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.tasks.Tasks.await
import com.lanazirot.anonymouschat.ui.providers.AppProvider
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.login.LoginData
import com.lanazirot.anonymouschat.ui.screens.login.LoginScreen
import com.lanazirot.anonymouschat.ui.screens.login.LoginViewModel
import com.lanazirot.anonymouschat.ui.screens.permissions.RequestPermission
import com.lanazirot.anonymouschat.ui.screens.splashscreen.App
import com.lanazirot.anonymouschat.ui.theme.AnonymousChatTheme
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import java.util.Timer
import kotlinx.coroutines.runBlocking
import org.junit.Before


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class LoginTest {
    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()



    @OptIn(ExperimentalPermissionsApi::class)
    @Test
    fun test_iniciar_sesion_correo_formato_incorrecto() {
        hiltRule.inject()
        composeTestRule.activity.setContent{
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
                        LoginScreen()

                    }
                }
            }
        }
        val loginViewModel = composeTestRule.activity.viewModels<LoginViewModel>().value
        loginViewModel.viewModelScope.launch {
            delay(5000)
        }
        val login = composeTestRule.activity.getString(R.string.login)
        val email = composeTestRule.activity.getString(R.string.email)
        val password = composeTestRule.activity.getString(R.string.password)
        val invalid_email = composeTestRule.activity.getString(R.string.val_email_invalid)
        composeTestRule.onNodeWithTag("loginImage").assertExists()
        composeTestRule.onNodeWithText(text = login).assertExists()
        composeTestRule.onNodeWithText(text = email).assertExists()
        composeTestRule.onNodeWithText(text = password).assertExists()
        composeTestRule.onNodeWithText(text = email).performTextInput("JISIDH")
        composeTestRule.onNodeWithText(text = email).performTextInput("JISIDH")
        composeTestRule.onNodeWithText(text = login).performClick()
        composeTestRule.onNodeWithText(text = invalid_email).assertExists()
    }

    @Test
    fun test_iniciar_sesion_contrasenia_vacia(){
        hiltRule.inject()
        composeTestRule.activity.setContent{
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
                        LoginScreen()

                    }
                }
            }
        }
        val loginViewModel = composeTestRule.activity.viewModels<LoginViewModel>().value
        loginViewModel.viewModelScope.launch {
            delay(5000)
        }
        val pass_empty = composeTestRule.activity.getString(R.string.val_pass_empty)
        val login = composeTestRule.activity.getString(R.string.login)
        val email = composeTestRule.activity.getString(R.string.email)
        val password = composeTestRule.activity.getString(R.string.password)
        composeTestRule.onNodeWithTag("loginImage").assertExists()
        composeTestRule.onNodeWithText(text = login).assertExists()
        composeTestRule.onNodeWithText(text = email).assertExists()
        composeTestRule.onNodeWithText(text = password).assertExists()
        composeTestRule.onNodeWithText(text = email).performTextInput("JISIDH")
        composeTestRule.onNodeWithText(text = login).performClick()
        composeTestRule.onNodeWithText(text = pass_empty).assertExists()
    }

    @Test
    fun test_iniciar_sesion_correo_vacio(){
        hiltRule.inject()
        composeTestRule.activity.setContent{
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
                        LoginScreen()

                    }
                }
            }
        }
        val loginViewModel = composeTestRule.activity.viewModels<LoginViewModel>().value
        loginViewModel.viewModelScope.launch {
            delay(5000)
        }
        val email_empty = composeTestRule.activity.getString(R.string.val_email_empty)
        val login = composeTestRule.activity.getString(R.string.login)
        val email = composeTestRule.activity.getString(R.string.email)
        val password = composeTestRule.activity.getString(R.string.password)
        composeTestRule.onNodeWithTag("loginImage").assertExists()
        composeTestRule.onNodeWithText(text = login).assertExists()
        composeTestRule.onNodeWithText(text = email).assertExists()
        composeTestRule.onNodeWithText(text = password).assertExists()
        composeTestRule.onNodeWithText(text = password).performTextInput("JISIDH")
        composeTestRule.onNodeWithText(text = login).performClick()
        composeTestRule.onNodeWithText(text = email_empty).assertExists()
    }
}




