package com.example.sinep.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.sinep.R

@Composable
fun RegistrationField(
    state: MutableState<String>,
    checkFields: MutableState<Boolean>,
    focusManager: FocusManager,
    labelText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false
) {
    TextField(
        value = state.value,
        onValueChange = {
            state.value = it
            checkFields.value = false
        },
        label = { Text(labelText) },
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done, keyboardType = keyboardType
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        trailingIcon = {
            Icon(
                painterResource(id = R.drawable.warning_img),
                "",
                tint = Color.Red,
                modifier = Modifier.alpha(if (checkFields.value && state.value == "") 1f else 0f)
            )
        }
    )
}