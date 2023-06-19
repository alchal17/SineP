package com.example.sinep.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sinep.R
import com.example.sinep.models.Post
import com.example.sinep.ui.theme.*

@Composable
fun UserInfoPost(post: Post) {
    var extended by remember {
        mutableStateOf(false)
    }
    val targetAspectRatio by animateFloatAsState(
        targetValue = if (extended) 1f else 3f,
        animationSpec = tween(durationMillis = 300)
    )
    Column(
        modifier = Modifier
            .padding(bottom = 15.dp, top = 15.dp, start = 5.dp, end = 5.dp)
            .background(color = PostBG),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),

                ) {
                Image(
                    painter = painterResource(id = post.user.profilePicture),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(35.dp)
                )
                Column() {
                    Row() {
                        Text(text = post.title, color = Color.White)
                        if (post.NSFW)
                            Text(
                                text = "[NSFW]",
                                color = Color.Red,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                    }
                    Text(text = "Time and date of the post", color = Color.Gray, fontSize = 10.sp)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.like_img),
                        contentDescription = "like",
                        tint = Color.White
                    )
                    Text(text = post.like.toString(), color = Color.White)
                }
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.dislike_img),
                        contentDescription = "dislike",
                        tint = Color.White
                    )
                    Text(text = post.dislike.toString(), color = Color.White)
                }
            }
        }
        Image(
            painter = painterResource(id = post.image),
            contentDescription = "post_image",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RectangleShape)
                .aspectRatio(targetAspectRatio)
                .clickable { extended = !extended },
            contentScale = ContentScale.FillWidth
        )
    }
}