package com.example.sinep.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.sinep.MainPage
import com.example.sinep.components.MyMessageComponent
import com.example.sinep.components.UserMessageComponent
import com.example.sinep.models.Chat

@Composable
fun Correspondence(context: MainPage, chat: Chat) {

    var messagesState by remember {
        mutableStateOf(chat.messages.toList())
    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth()
    ) {
        items(messagesState) { message ->
            if (message.user == context.user) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp)),
                    ) {
                        MyMessageComponent(user = message.user, message = message.text)
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp)),
                    ) {
                        UserMessageComponent(user = message.user, message = message.text)
                    }
                }
            }
        }
    }
}