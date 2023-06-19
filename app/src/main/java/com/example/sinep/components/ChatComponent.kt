package com.example.sinep.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.sinep.ui.theme.Lightgray
import com.example.sinep.ui.theme.PostBG
import com.example.sinep.models.Chat
import com.example.sinep.MainPage

@Composable
fun ChatComponent(chat: Chat, navController: NavController, context: MainPage) {
    val registeredUser = context.user
    val user = chat.users.filter { user -> user != registeredUser }.first()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(PostBG)
            .padding(start = 3.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "correspondence",
                    value = chat
                )
                navController.navigate("correspondence")
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = user.profilePicture),
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(42.dp)
                .clip(CircleShape),
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = user.nickname,
                style = TextStyle(color = Color.White, fontSize = 15.sp)
            )
            Text(text = chat.messages.last().text, style = TextStyle(color = Lightgray), maxLines = 1)
        }
    }
}