package com.example.sinep.components

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sinep.models.Post
import com.example.sinep.models.User
import com.example.sinep.screens.PostsScreen
import com.example.sinep.screens.PostDetail

@Composable
fun PostsNavHost(posts: List<Post>) {
    val navController = rememberNavController()
    val scrollState = rememberLazyListState()
    NavHost(navController = navController, startDestination = "posts") {
        composable("posts") {
            PostsScreen(posts, navController, scrollState)
        }

        composable("post_detail") {
            val post = navController.previousBackStackEntry?.savedStateHandle?.get<Post>("post")
            post?.let { post -> PostDetail(post) }
        }
    }
}