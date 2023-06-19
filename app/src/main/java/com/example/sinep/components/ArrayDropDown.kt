package com.example.sinep.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ArrayDropDown(state: MutableState<String>, options: Array<String>, checkFields: MutableState<Boolean>) {
    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(Modifier.fillMaxWidth()) {
        TextField(
            value = state.value,
            onValueChange = {
                state.value = it
            },
            label = {
                Text(
                    "Gender",
                    style = TextStyle(
                        color = if (checkFields.value && state.value == "") Color.Red else Color.Unspecified,
                        fontSize = 16.sp
                    )
                )
            },
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .fillMaxWidth(),
            trailingIcon = {
                Icon(
                    icon,
                    "",
                    Modifier.clickable { expanded = !expanded },
                    tint = if (checkFields.value && state.value == "") Color.Red else Color.Black
                )
            },
            readOnly = true
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            options.forEach { label ->
                DropdownMenuItem(onClick = {
                    checkFields.value = false
                    state.value = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }

}