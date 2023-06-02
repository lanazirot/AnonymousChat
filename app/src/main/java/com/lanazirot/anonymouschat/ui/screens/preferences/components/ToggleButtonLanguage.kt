package com.lanazirot.anonymouschat.ui.screens.preferences.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lanazirot.anonymouschat.R

@Composable
fun ToggleButtonLanguage(
    currentLanguage: String,
    onChange: (locale: String) -> Unit
) {
    val languages = listOf(
        listOf("es", stringResource(R.string.spanish)),
        listOf("en", stringResource(R.string.english)),
    )

    var selectedOption by remember { mutableStateOf(currentLanguage) }
    val onSelectionChange = { id: String ->
        selectedOption = id
        onChange(id)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
        //modifier = Modifier.fillMaxSize(),
    ) {
        languages.forEach { lan ->
            Row(
                modifier = Modifier
                    .padding(
                        all = 8.dp,
                    ),
            ) {
                Text(
                    text = lan[1],
                    style = typography.body1.merge(),
                    color = if (lan[0] == selectedOption) {
                        Color.Black
                    } else {
                        Color.White
                    },
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(
                                size = 12.dp,
                            ),
                        )
                        .clickable {
                            onSelectionChange(lan[0])
                        }
                        .background(
                            if (lan[0] == selectedOption) {
                                Color.White
                            } else {
                                Color.LightGray
                            }
                        )
                        .padding(
                            vertical = 12.dp,
                            horizontal = 16.dp,
                        ),
                )
            }
        }
    }
}
