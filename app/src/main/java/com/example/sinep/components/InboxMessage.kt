package com.example.sinep.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.sinep.R
import com.example.sinep.models.InboxMessage

@Composable
fun InboxMessage(message: InboxMessage) {
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 10.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.email),
                contentDescription = "email",
                colorFilter = ColorFilter.tint(
                    Color.White
                )
            )
            Text(text = "from ${message.user.nickname}", style = TextStyle(color = Color.White))
        }
        Text(text = message.text, style = TextStyle(color = Color.White))
    }
}