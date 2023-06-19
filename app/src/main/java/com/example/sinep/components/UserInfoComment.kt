package com.example.sinep.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sinep.models.Comment
import com.example.sinep.R

@Composable
fun UserInfoComment(comment: Comment) {
    Column(
        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp, start = 5.dp, end = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ) {
            Row() {
                Image(
                    painter = painterResource(id = comment.user.profilePicture),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(35.dp),
                )
                Column() {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(text = comment.post.title, color = Color.White)
                        if (comment.post.NSFW)
                            Text(text = "[NSFW]", color = Color.Red)
                    }
                    Text(text = "Time and date of the post", color = Color.Gray, fontSize = 10.sp)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.like_img),
                        contentDescription = "like",
                        tint = Color.White
                    )
                    Text(text = comment.like.toString(), color = Color.White)
                }
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.dislike_img),
                        contentDescription = "dislike",
                        tint = Color.White
                    )
                    Text(text = comment.dislike.toString(), color = Color.White)
                }

            }
        }
        Text(text = comment.content, color = Color.White, modifier = Modifier.padding(start = 5.dp))
    }
}