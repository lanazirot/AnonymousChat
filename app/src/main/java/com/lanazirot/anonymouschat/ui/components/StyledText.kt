package com.lanazirot.anonymouschat.domain.models.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StyledText(value: String, text : String, onValueChange: (String) -> Unit, visualTransformation: VisualTransformation) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
//                    style = Anonymous,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.h2.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }},
        visualTransformation = visualTransformation,
        shape = CutCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(213, 213, 213 )
        )
    )
}