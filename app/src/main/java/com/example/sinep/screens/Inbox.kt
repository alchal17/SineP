package com.example.sinep.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sinep.ui.theme.*
import com.example.sinep.components.InboxInbox
import com.example.sinep.components.InboxMessage
import com.example.sinep.models.User
import com.example.sinep.R
import com.example.sinep.models.InboxInbox
import com.example.sinep.models.InboxMessage

@Composable
fun Inbox() {
    val navController = rememberNavController()
    val currentBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val inboxTargetColor by animateColorAsState(
        targetValue = Color.White.copy(if (currentRoute.equals("messages_section")) 0.5f else 1f),
        animationSpec = tween(300)
    )
    val messagesTargetColor by animateColorAsState(
        targetValue = Color.White.copy(if (currentRoute.equals("inbox_section")) 0.5f else 1f),
        animationSpec = tween(300)
    )
    val user = User(nickname = "Sasha", R.drawable.wb)
    val inboxInbox = listOf(
        InboxInbox(
            "name",
            "wknewewdewdef fwefewfineiwfnewfewf wefew efewf ewfewfewnfewnf ererivnreinvren",
            user
        ),
        InboxInbox(
            "name",
            "wknewewdewdef fwefewfineiwfnewfewf wefew efewf ewfewfewnfewnf ererivnreinvren wknewewdewdef fwefewfineiwfnewfewf wefew efewf ewfewfewnfewnf ererivnreinvren wknewewdewdef fwefewfineiwfnewfewf wefew efewf ewfewfewnfewnf ererivnreinvren wknewewdewdef fwefewfineiwfnewfewf wefew efewf ewfewfewnfewnf ererivnreinvren wknewewdewdef fwefewfineiwfnewfewf wefew efewf ewfewfewnfewnf ererivnreinvren wknewewdewdef fwefewfineiwfnewfewf wefew efewf ewfewfewnfewnf ererivnreinvren wknewewdewdef fwefewfineiwfnewfewf wefew efewf ewfewfewnfewnf ererivnreinvren wknewewdewdef fwefewfineiwfnewfewf wefew efewf ewfewfewnfewnf ererivnreinvren",
            user
        ),
        InboxInbox(
            "name",
            "wknewewdewdef fwefewfineiwfnewfewf wefew efewf ewfewfewnfewnf ererivnreinvren",
            user
        ),
    )
    val inboxMessages = listOf(
        InboxMessage(
            user,
            "emfewkfmkewfreokfmer3f ewfefoefn3onf3onf43uofnfno3nfo3nf43fn3 3nf3fn34fnu34nodemdmefub34ff"
        ),
        InboxMessage(
            user,
            "emfewkfmkewfreokfmer3f ewfefoefn3onf3onf43uofnfno3nfo3nf43fn3 3nf3fn34fnu34nodemdmefub34ff"
        ),
        InboxMessage(
            user,
            "emfewkfmkewfreokfmer3f ewfefoefn3onf3onf43uofnfno3nfo3nf43fn3 3nf3fn34fnu34nodemdmefub34ff"
        ),
        InboxMessage(
            user,
            "emfewkfmkewfreokfmer3f ewfefoefn3onf3onf43uofnfno3nfo3nf43fn3 3nf3fn34fnu34nodemdmefub34ff"
        ),
        InboxMessage(
            user,
            "hello"
        ),
    )
    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .background(PostBG)
            .fillMaxHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp)
        ) {
            Text(text = "INBOX",
                color = inboxTargetColor,
                modifier = Modifier.clickable {
                    if (currentRoute.equals("messages_section"))
                        navController.navigate("inbox_section")
                })
            Text(text = "MESSAGES",
                color = messagesTargetColor,
                modifier = Modifier.clickable {
                    if (currentRoute.equals("inbox_section"))
                        navController.navigate("messages_section") })
        }
        NavHost(navController = navController, startDestination = "inbox_section") {
            composable("inbox_section") {
                val lazyListState = rememberLazyListState()
                LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    itemsIndexed(inboxInbox) { index, inboxNotification ->
                        if (index == 0)
                            Divider(
                                color = Color.Gray,
                                modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
                            )
                        InboxInbox(
                            notification = inboxNotification
                        )
                        Divider(
                            color = Color.Gray,
                            modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
                        )

                    }
                }
            }
            composable("messages_section") {
                LazyColumn {
                    itemsIndexed(inboxMessages) { index, inboxMessage ->
                        if (index == 0)
                            Divider(
                                color = Color.Gray,
                                modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
                            )
                        InboxMessage(message = inboxMessage)
                        Divider(
                            color = Color.Gray,
                            modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
                        )
                    }
                }
            }
        }
    }
}