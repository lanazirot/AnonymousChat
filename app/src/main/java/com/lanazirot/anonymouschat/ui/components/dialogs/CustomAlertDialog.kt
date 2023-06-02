package com.lanazirot.anonymouschat.ui.components.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material.AlertDialog
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.lanazirot.anonymouschat.R

@Composable
fun CustomAlertDialog(message: String, dismissDialog: () -> Unit) {
    val warning = painterResource(R.drawable.warning)

    AlertDialog(
        onDismissRequest = {
            dismissDialog()
        },
        title = {
            Column(Modifier.fillMaxWidth()) {
                Image(
                    painter = warning,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(150.dp),
                    contentDescription = stringResource(R.string.warning)
                )
            }
        },
        text = {
            Text(
                text = message,
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        confirmButton = {
            Column(Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        dismissDialog()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(stringResource(R.string.ok))
                }
            }
        },
    )
}