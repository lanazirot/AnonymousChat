package com.lanazirot.anonymouschat.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lanazirot.anonymouschat.R

val Anonymous = FontFamily(
    Font(R.font.typewriter, FontWeight.Normal),
    Font(R.font.anonymouschat, FontWeight.Black)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Anonymous,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)