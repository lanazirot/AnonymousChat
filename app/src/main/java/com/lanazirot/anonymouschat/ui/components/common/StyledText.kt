package com.lanazirot.anonymouschat.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StyledText(value: String, text: String, onValueChange: (String) -> Unit, visualTransformation: VisualTransformation) {
    val isTextFieldFocused = remember { mutableStateOf(false) }
    val isPasswordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
<<<<<<< HEAD
            if (!isTextFieldFocused.value && value.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h2.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Normal
                        )
=======
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h2.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal
>>>>>>> nuevodiseño
                    )
                }
            }
        },
        visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else visualTransformation,
        modifier = Modifier.fillMaxWidth(),
        shape = CutCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(213, 213, 213),
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        trailingIcon = {
            if (visualTransformation != VisualTransformation.None) {
                IconButton(
                    onClick = { isPasswordVisible.value = !isPasswordVisible.value },
                ) {
                    Icon(
                        imageVector = if (isPasswordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (isPasswordVisible.value) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            }
        }
    )
}
