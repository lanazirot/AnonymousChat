package com.lanazirot.anonymouschat.domain.models.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanazirot.anonymouschat.R

@Composable
fun Drawer (
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(Color(83, 83, 83))
            .padding(start = 25.dp)
    ) {
        Row(modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.iusuario),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 16.dp)
            )
            Text(
                text = "Usuario",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        screens.forEach { screen ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(screen.icon),
                    contentDescription = "null",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = screen.title,
                    color = Color.White,
                    fontSize = 21.sp,
                    modifier = Modifier.clickable {
                        onDestinationClicked(screen.route)
                    }
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}