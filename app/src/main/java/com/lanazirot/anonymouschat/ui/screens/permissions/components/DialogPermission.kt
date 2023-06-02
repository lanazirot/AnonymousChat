package com.lanazirot.anonymouschat.ui.screens.permissions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lanazirot.anonymouschat.R

@Composable
fun DialogPermission(
    title: String? = "Title",
    desc: String? = "Default dialog description",
    enableLocation: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Dialog(
        onDismissRequest = { enableLocation.value = false }
    ) {
        Box(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(25.dp, 5.dp, 25.dp, 5.dp)
                )
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title!!,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                        .testTag("dialog_title"),
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primary,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = desc!!,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                        .testTag("dialog_desc"),
                    letterSpacing = 1.sp,
                    style = MaterialTheme.typography.body2,
                    color = Color.Red,
                )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp)
                        .testTag("btn_accept_permission"),
                    onClick = onClick,
                    contentPadding = PaddingValues(),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                    ),
                    shape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp)
                ) {
                    Text(
                        text = stringResource(R.string.ok),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                            .testTag("dialog_otorgar_button"),
                        letterSpacing = 2.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.button,
                        color = Color.Gray,
                    )
                }
            }
        }
    }
}