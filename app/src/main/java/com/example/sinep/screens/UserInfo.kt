package com.example.sinep.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sinep.R
import com.example.sinep.components.UserInfoComment
import com.example.sinep.models.Comment
import com.example.sinep.models.Post
import com.example.sinep.models.User
import com.example.sinep.ui.theme.LinkText
import com.example.sinep.ui.theme.PostBG
import com.example.sinep.components.UserInfoPost
import com.example.sinep.components.UserInfoScore

@Composable
fun UserInfo(user: User, posts: List<Post>, comments: List<Comment>) {
    val navController = rememberNavController()
    val currentBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val postsTargetColor by animateColorAsState(
        targetValue = Color.White.copy(if (currentRoute.equals("posts")) 1f else 0.5f),
        animationSpec = tween(300)
    )
    val commentsTargetColor by animateColorAsState(
        targetValue = Color.White.copy(if (currentRoute.equals("comments")) 1f else 0.5f),
        animationSpec = tween(300)
    )
    val scoreTargetColor by animateColorAsState(
        targetValue = Color.White.copy(if (currentRoute.equals("score")) 1f else 0.5f),
        animationSpec = tween(300)
    )
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1 / 3f)
                .padding(top = 10.dp)
                .background(color = PostBG),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.wb),
                    contentDescription = "profile_bg",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1.2f),
                    contentScale = ContentScale.FillBounds
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Bio\n${user.bio}", color = Color.White)
                        Text(
                            text = "Change",
                            color = LinkText,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth(0.9f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.wb),
                    contentDescription = "user_avatar",
                    modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(60.dp)
                )
                Text(
                    text = user.nickname,
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PostBG)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Posts",
                    color = postsTargetColor,
                    modifier = Modifier.clickable {
                        if (currentRoute != "posts")
                            navController.navigate("posts")
                    })
                Text(
                    text = "Comments",
                    color = commentsTargetColor,
                    modifier = Modifier.clickable {
                        if (currentRoute != "comments")
                            navController.navigate("comments")
                    })
                Text(
                    text = "Score",
                    color = scoreTargetColor,
                    modifier = Modifier.clickable {
                        if (currentRoute != "score")
                            navController.navigate("score")
                    })
            }
            NavHost(navController = navController, startDestination = "posts") {
                composable("posts") {
                    LazyColumn {
                        items(posts) { item ->
                            Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)
                            UserInfoPost(post = item)
                        }
                    }
                }
                composable("comments") {
                    LazyColumn {
                        itemsIndexed(comments) { index, item ->
                            if (index == 0)
                                Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)
                            UserInfoComment(comment = item)
                            Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)
                        }
                    }
                }
                composable("score") {
                    UserInfoScore(commentsNumber = comments.size, comment = comments[0], postsNumber = posts.size, post = posts[0])
                }
            }
        }
    }
}