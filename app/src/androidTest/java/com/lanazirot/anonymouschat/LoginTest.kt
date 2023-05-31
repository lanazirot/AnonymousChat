package com.lanazirot.anonymouschat

import android.Manifest
import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
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
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.tasks.Tasks.await
import com.lanazirot.anonymouschat.ui.providers.AppProvider
import com.lanazirot.anonymouschat.ui.screens.login.LoginData
import com.lanazirot.anonymouschat.ui.screens.login.LoginScreen
import com.lanazirot.anonymouschat.ui.screens.login.LoginViewModel
import com.lanazirot.anonymouschat.ui.screens.permissions.RequestPermission
import com.lanazirot.anonymouschat.ui.screens.splashscreen.App
import com.lanazirot.anonymouschat.ui.theme.AnonymousChatTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
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
    @get:Rule
    var composeTestRule = createComposeRule()

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun test_iniciar_sesion_correo_formato_correcto() {
        hiltRule.inject()

        composeTestRule.setContent {
            LoginScreen()
        }
        composeTestRule.onNodeWithTag("loginButton").assertExists()
        composeTestRule.onNodeWithText("Correo electrónico").performTextInput("corre@@Dxd . w.")
        composeTestRule.onNodeWithText("Iniciar sesión").performClick()
        composeTestRule.onNodeWithText("Email invalido").assertExists()
    }
}

