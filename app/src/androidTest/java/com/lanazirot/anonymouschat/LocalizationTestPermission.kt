package com.lanazirot.anonymouschat

import android.Manifest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.lanazirot.anonymouschat.ui.providers.AppProvider
import com.lanazirot.anonymouschat.ui.screens.permissions.RequestPermission
import com.lanazirot.anonymouschat.ui.screens.splashscreen.App
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

//@RunWith(AndroidJUnit4::class)
//@HiltAndroidTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class LocalizationTestPermission {

//    @get:Rule(order = 1)
//    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalPermissionsApi::class, ExperimentalTestApi::class)
    @Test
    fun test_primer_pantalla_aceptar_permiso() {
        //hiltRule.inject()
        composeTestRule.setContent {

            val navController = rememberNavController()
            val gp = AppProvider(navController = navController)
            RequestPermission(permission = Manifest.permission.ACCESS_FINE_LOCATION) {
                App(navController)
            }



        }
        composeTestRule.onNodeWithText("Aceptar").assertExists()
        composeTestRule.onNodeWithText("Aceptar").performClick()
    }
}