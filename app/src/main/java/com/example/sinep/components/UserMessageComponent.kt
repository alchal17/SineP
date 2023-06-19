package com.example.sinep.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sinep.models.User
import com.example.sinep.ui.theme.*

@Composable
fun UserMessageComponent(user: User, message: String) {
    Box(
        modifier = Modifier
            .background(PostBG)
            .fillMaxWidth(0.6f)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 7.dp),
            horizontalAlignment = Alignment.Start

        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Image(
                    painter = painterResource(id = user.profilePicture),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = user.nickname,
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Text(text = message, color = Color.White)
        }
    }
}