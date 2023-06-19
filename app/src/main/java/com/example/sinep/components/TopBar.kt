package com.example.sinep.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sinep.R
import com.example.sinep.scripts.dpToPx
import com.example.sinep.ui.theme.Dark
import com.example.sinep.ui.theme.TopPanel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopBar(swipeableState: SwipeableState<Int>, navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val swipableStateValue = swipeableState.offset
    val currentBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Dark)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    TopPanel
                )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { coroutineScope.launch { swipeableState.animateTo(if (swipableStateValue.value == 0f) 1 else 0) } }) {
                    Icon(
                        painter = painterResource(id = R.drawable.density_medium_img),
                        contentDescription = "Search img",
                        tint = Color.White
                    )
                }
            }
            Row {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_img),
                        contentDescription = "Search img",
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    if (currentRoute != "user_info") navController.navigate("user_info")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile_img),
                        contentDescription = "Search img",
                        tint = Color.White
                    )
                }
            }
        }
    }
}