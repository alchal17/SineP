package com.example.sinep.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.sinep.R
import com.example.sinep.models.DiscoverTopic
import com.example.sinep.components.DiscoverTopicCard
import java.nio.file.WatchEvent.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Discover() {
    val topics = listOf(
        DiscoverTopic("Memes", R.drawable.wb, 420, false),
        DiscoverTopic("Art", R.drawable.wb, 520, false),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
        DiscoverTopic("Cheerlead", R.drawable.wb, 620, true),
    )
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
    ) {
        items(topics) { topic -> DiscoverTopicCard(topic = topic) }
    }
}