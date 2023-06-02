package com.lanazirot.anonymouschat.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.anonymouschat.ui.screens.preferences.ThemeViewModel

@Composable
fun StyledText(
    value: String,
    text: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation
) {
    val isPasswordVisible = remember { mutableStateOf(false) }
    val themeViewModel: ThemeViewModel = hiltViewModel()
    val isDarkThemeEnabled by themeViewModel.isDarkThemeEnabled.collectAsState()

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = text,
                color = if (isDarkThemeEnabled) Color.White else Color.Black,
                //color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        },
        textStyle = LocalTextStyle.current.copy(
            color = if (isDarkThemeEnabled) Color.White else Color.Black
            //color = Color.Black
        ),
        visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else visualTransformation,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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