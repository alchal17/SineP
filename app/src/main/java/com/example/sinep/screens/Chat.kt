package com.example.sinep.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sinep.MainPage
import com.example.sinep.R
import com.example.sinep.components.ChatComponent
import com.example.sinep.models.User
import com.example.sinep.models.Chat
import com.example.sinep.models.Message
import com.example.sinep.models.Post

@Composable
fun Chats(
    context: MainPage,
    navController: NavController,
    chats: List<Chat>,
    filteredChats: MutableState<List<Chat>>
) {
    var searchUsers by remember {
        mutableStateOf("")
    }

//    var filteredChats by remember {
//        mutableStateOf<List<Chat>>(chats)
//    }
    val focusManager = LocalFocusManager.current

    val customTextFieldColors = TextFieldDefaults.textFieldColors(
        disabledTextColor = Color.Transparent,
        backgroundColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        cursorColor = Color.Blue,
        textColor = Color.Black
    )

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Search for users:",
            color = Color.White,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        TextField(
            value = searchUsers,
            onValueChange = { searchValue ->
                searchUsers = searchValue
                filteredChats.value =
                    if (searchValue == "") chats else chats.filter { chat ->
                        searchUsers in chat.users.filter { it != context.user }.map { it.nickname }
                            .first()
                    }

            },
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.Gray),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            trailingIcon = {
                Icon(
                    painterResource(id = R.drawable.search_img),
                    "",
                    tint = Color.Black,
                )
            },
            colors = customTextFieldColors
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(1.dp),
            modifier = Modifier.padding(top = 50.dp)
        ) {
            items(filteredChats.value) {
                ChatComponent(chat = it, navController = navController, context = context)
            }
        }
    }
}

@Composable
fun ChatBottomBar(chat: Chat, context: MainPage, filteredChats: MutableState<List<Chat>>) {
    var message by remember {
        mutableStateOf("")
    }
    val customTextFieldColors = TextFieldDefaults.textFieldColors(
        disabledTextColor = Color.Transparent,
        backgroundColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        cursorColor = Color.Black,
        textColor = Color.Black
    )
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TextField(
            value = message,
            onValueChange = { message = it },
            colors = customTextFieldColors,
            placeholder = { Text(text = "Send message...") },
            modifier = Modifier.weight(1f),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
        IconButton(
            onClick = {
//                chat.messages.add(Message(context.user, message))
//                val user = chat.users.filter { user -> user != context.user }.first()
//                val chat = filteredChats.value.filter { user in it.users }.forEach()
            },
            enabled = message != ""
        ) {
            Icon(
                painter = painterResource(id = R.drawable.send_message),
                contentDescription = "send message",
                tint = Color.White
            )
        }
    }
}

@Composable
fun ChatNavHost(context: MainPage) {
    val navController = rememberNavController()
    val user = User("sasha", R.drawable.wb)
    val user2 = User("gleb", R.drawable.wb)
    val user3 = User("anton", R.drawable.wb)

    val chat1Messages = mutableListOf<Message>(
        Message(user, "Hello"),
        Message(context.user, "Hi"),
        Message(user, "How are you?"),
        Message(context.user, "Fine"),
    )
    val chat2Messages = mutableListOf<Message>(
        Message(user2, "Hello"),
        Message(context.user, "Hi"),
        Message(user2, "How are you?"),
        Message(context.user, "Fine"),
    )
    val chat3Messages = mutableListOf<Message>(
        Message(user3, "Hello"),
        Message(context.user, "Hi"),
        Message(user3, "How are you?"),
        Message(context.user, "Fine"),
    )

    val chat1 = Chat(setOf(user, context.user), chat1Messages)
    val chat2 = Chat(setOf(user2, context.user), chat2Messages)
    val chat3 = Chat(setOf(user3, context.user), chat3Messages)
    val chats = listOf(chat1, chat2, chat3)
    val filteredChats = remember {
        mutableStateOf(chats)
    }
    NavHost(navController = navController, startDestination = "chats") {
        composable("chats") {
            Chats(context, navController, chats, filteredChats)
        }
        composable("correspondence") {
            val chat =
                navController.previousBackStackEntry?.savedStateHandle?.get<Chat>("correspondence")
            if (chat != null) {
                Scaffold(
                    bottomBar = { ChatBottomBar(chat, context, filteredChats) },
                    backgroundColor = Color.Black
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(
                            PaddingValues(
                                0.dp,
                                0.dp,
                                0.dp,
                                innerPadding.calculateBottomPadding()
                            )
                        )
                    ) {
                        Correspondence(context = context, chat = chat)
                    }
                }
            }
        }
    }
}