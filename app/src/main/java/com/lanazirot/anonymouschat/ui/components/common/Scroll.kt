package com.lanazirot.anonymouschat.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScrollableScreen(listItems: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        for (item in listItems) {
            Text(
                text = item,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Justify,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}