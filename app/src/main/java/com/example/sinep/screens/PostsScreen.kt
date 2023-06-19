package com.example.sinep.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sinep.R
import com.example.sinep.components.PostComponents
import com.example.sinep.components.SideBar
import com.example.sinep.models.Post
import com.example.sinep.scripts.dpToPx
import com.example.sinep.ui.theme.Dark
import com.example.sinep.ui.theme.TopPanel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostsScreen(
    posts: List<Post>,
    navControler: NavController,
    scrollState: LazyListState,
) {
    val swipeableState = rememberSwipeableState(initialValue = 1)

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp), state = scrollState,
    ) {
        items(posts) { post ->
            PostComponents(
                post,
                navControler,
                swipeableState
            )
        }
    }

}

