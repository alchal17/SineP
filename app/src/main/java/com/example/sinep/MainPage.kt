package com.example.sinep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sinep.components.BottomNavItem
import com.example.sinep.components.PostsNavHost
import com.example.sinep.components.SideBar
import com.example.sinep.components.TopBar
import com.example.sinep.models.Comment
import com.example.sinep.models.Post
import com.example.sinep.models.User
import com.example.sinep.screens.AddPost
import com.example.sinep.screens.Discover
import com.example.sinep.screens.Inbox
import com.example.sinep.screens.UserInfo
import com.example.sinep.screens.ChatNavHost
import com.example.sinep.scripts.dpToPx
import kotlin.math.roundToInt


class MainPage : ComponentActivity() {
    lateinit var navController: NavHostController
    lateinit var user: User

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val login = intent.getStringExtra("login")
        user = User(login ?: "", R.drawable.wb, "Let's be kind!")
        setContent {
            navController = rememberNavController()
            val sideBarWidth = LocalConfiguration.current.screenWidthDp.toFloat() / 2
            val swipeableState = rememberSwipeableState(initialValue = 1)
            val currentBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route
            val context = LocalContext.current

            Scaffold(modifier = Modifier.swipeable(
                state = swipeableState,
                anchors = mapOf(0f to 0, -dpToPx(context, sideBarWidth) to 1),
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            ),
                backgroundColor = Color.Black,
                topBar = { TopBar(swipeableState, navController) },
                bottomBar = {
                    BottomNavigationBar(
                        items = listOf(
                            BottomNavItem(
                                name = "Home", route = "home", icon_id = R.drawable.home
                            ),
                            BottomNavItem(
                                name = "Discover", route = "discover", icon_id = R.drawable.compass
                            ),
                            BottomNavItem(
                                name = "Add", route = "add", icon_id = R.drawable.plus
                            ),
//                            BottomNavItem(
//                                name = "Chat", route = "chat", icon_id = R.drawable.chat
//                            ),
                            BottomNavItem(
                                name = "Inbox",
                                route = "inbox",
                                icon_id = R.drawable.bell,
                                counter = 40
                            ),
                        ),
                        navController = navController,
                        onItemClick = {
                            if (it.route != currentRoute)
                                navController.navigate(it.route)
                        },
                    )
                }) { inner_padding ->
                Box(
                    modifier = Modifier.padding(
                        PaddingValues(
                            0.dp, 0.dp, 0.dp, inner_padding.calculateBottomPadding()
                        )
                    )
                ) {
                    Navigation(
                        this@MainPage
                    )
                }
                Box(modifier = Modifier.offset {
                    IntOffset(
                        swipeableState.offset.value.roundToInt(),
                        0
                    )
                }) {
                    SideBar()
                }
            }
        }
    }
}

@Composable
fun Navigation(context: MainPage) {
    val navController = context.navController
    val sasha = User("Sasha1337", R.drawable.wb)
    val posts = listOf(
        Post(
            "Let's be kind!",
            "You know, I really respect all the race",
            R.drawable.wb,
            sasha,
            like = 500,
            dislike = 9
        ),
        Post(
            "Let's be kind!",
            "You know, I really respect all the race",
            R.drawable.wb,
            sasha,
            like = 500,
            dislike = 9,
            NSFW = false
        ),
        Post(
            "Avocado",
            "Avocado growth",
            R.drawable.avocado,
            sasha,
            like = 500,
            dislike = 9,
            NSFW = false
        ),
    )
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            PostsNavHost(posts = posts)
        }
        composable("discover") {
            Discover()
        }
        composable("add") {
            AddPost()
        }
        composable("chat") {
            ChatNavHost(context = context)
        }
        composable("inbox") {
            Inbox()
        }
        composable("user_info") {
            val user = User("sasha", R.drawable.wb, "Let's be kind!")
            val post = Post("Let's be kind!", "Let's be kind!", R.drawable.wb, user, true)
            val comments = listOf(
                Comment(
                    "Nice! ewnkewfnwenjewnjewnfenojnnjwewjonewnqwkdqwdnqwd ewnjewncewjncewcnewfneewjnwkqwdnjeewndmewnf ewfnewnewfnwemewihfewbfewuofbeoubf",
                    post,
                    user
                ),
                Comment("Nice!", post, user, like = 1337, dislike = 228),
                Comment("Nice!", post, user),
                Comment("Nice!", post, user),
            )
            UserInfo(user = context.user, posts = posts, comments = comments)
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        backgroundColor = Color.DarkGray, elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(selected = selected,
                onClick = {
                    onItemClick(item)
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box() {
                            Icon(
                                painter = painterResource(id = item.icon_id),
                                contentDescription = item.name,
                                modifier = Modifier.size(25.dp)
                            )
                            if (item.counter > 0) {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .background(Color.Red, CircleShape)
                                        .align(Alignment.TopEnd),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = item.counter.toString(),
                                        color = Color.White,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                        if (selected) {
                            Text(
                                text = item.name, textAlign = TextAlign.Center, fontSize = 10.sp
                            )
                        }
                    }
                })
        }
    }
}