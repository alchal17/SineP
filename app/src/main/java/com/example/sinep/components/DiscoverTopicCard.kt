package com.example.sinep.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sinep.models.DiscoverTopic
import com.example.sinep.ui.theme.PostBG

@Composable
fun DiscoverTopicCard(topic: DiscoverTopic) {
    Card(
        backgroundColor = PostBG,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .clip(
                RoundedCornerShape(15.dp)
            )
    ) {
        Column() {
            Row(
                modifier = Modifier
//                    .padding(horizontal = 15.dp)
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(
                        text = topic.name,
                        style = TextStyle(color = Color.White, fontSize = 10.sp)
                    )
                    if (topic.NSFW)
                        Text(
                            text = "[NSFW]",
                            style = TextStyle(color = Color.Red, fontSize = 10.sp)
                        )
                }
                Text(
                    text = topic.members.toString(),
                    style = TextStyle(color = Color.White, fontSize = 10.sp)
                )
            }
            Image(
                painter = painterResource(id = topic.image_id),
                contentDescription = topic.name,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(10.dp)),
            )
        }
    }
}