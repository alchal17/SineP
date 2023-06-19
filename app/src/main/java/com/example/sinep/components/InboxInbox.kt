package com.example.sinep.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.sinep.ui.theme.PostBG
import com.example.sinep.models.InboxInbox

@Composable
fun InboxInbox(notification: InboxInbox) {
    Box(
        modifier = Modifier
            .background(PostBG)
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                Image(
                    painter = painterResource(notification.user.profilePicture),
                    contentDescription = "avatart",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                )
                Text(text = notification.name, style = TextStyle(color = Color.White))
            }
            Text(text = notification.text, style = TextStyle(color = Color.White))
        }
    }
}