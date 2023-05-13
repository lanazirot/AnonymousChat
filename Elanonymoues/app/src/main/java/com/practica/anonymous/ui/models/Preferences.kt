package com.practica.anonymous.ui.models

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Preferences() {
//Make it beauty
    Column(
        modifier = Modifier.fillMaxWidth()

    ) {

        Row(


        ) {
            Column(

            ) {
                Text(text = "Language")
            }
            Column(
modifier = Modifier
                    .fillMaxWidth()


            ) {
                DropDownMenuExample(thelist = listOf("English", "Spanish", "French"))
            }
        }

        Row(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            )
            {
                Text(text = "Tamaño del texto")
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            //An number input text
            var text by remember { mutableStateOf("0") }
            var number by remember { mutableStateOf(0) }

            TextField(value = text, onValueChange = {
                text = it
                try {
                    number = it.toInt()
                } catch (e: Exception) {
                    number = 0
                }

            })


        }
    }
}



@Composable
fun DropDownMenuExample(thelist: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    val items = thelist
    var lang by remember { mutableStateOf("English") }
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = lang,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    lang = s
                }, text = { Text(text = s) })
            }
        }
    }
}
