package com.lanazirot.anonymouschat.ui.screens.permissions


import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.lanazirot.anonymouschat.ui.screens.permissions.components.DialogPermission

@ExperimentalPermissionsApi
@Composable
fun RequestPermission(
    permission: String,
    message: String = "Para usar esta funcionalidad necesitamos que nos des permiso para acceder a tu ubicación.",
    content: @Composable () -> Unit
) {
    val permissionState = rememberPermissionState(permission)

    HandlePermission(
        permissionState = permissionState,
        onDeniedComposable = { shouldShowRationale ->
            PermissionDeniedContent(
                rationaleMessage = message,
                shouldShowRationale = shouldShowRationale
            ) { permissionState.launchPermissionRequest() }
        },
        content = { content() }
    )
}

@ExperimentalPermissionsApi
@Composable
fun HandlePermission(
    permissionState: PermissionState,
    onDeniedComposable: @Composable (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    when (permissionState.status) {
        is PermissionStatus.Granted -> content()
        is PermissionStatus.Denied -> onDeniedComposable(permissionState.status.shouldShowRationale)
    }
}


@Composable
fun Content(showButton: Boolean = true, onClick: () -> Unit) {
    if (showButton) {
        val enableLocation = remember { mutableStateOf(true) }
        if (enableLocation.value)
            DialogPermission(
                title = "Enciende el GPS",
                desc = "AnonymousChat necesita saber tu ubicación en tiempo real. Tranquilo, no compartiremos tu ubicación con nadie. Solo la usaremos para mostrarte personas cercanas a ti.",
                enableLocation,
                onClick
            )

    }
}

@ExperimentalPermissionsApi
@Composable
fun PermissionDeniedContent(
    rationaleMessage: String,
    shouldShowRationale: Boolean,
    onRequestPermission: () -> Unit
) = if (shouldShowRationale) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                text = "Petición de permiso",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        text = {
            Text(text = rationaleMessage, modifier = Modifier.testTag("dialog_description"))
        },
        confirmButton = {
            Button(onClick = onRequestPermission, modifier = Modifier.testTag("dialog_otorgar_button")) {
                Text("Otorgar permiso")
            }
        },

    )

} else {
    Content(onClick = onRequestPermission)
}