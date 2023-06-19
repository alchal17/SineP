package com.example.sinep.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import com.example.sinep.models.Comment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sinep.ui.theme.Lightgray
import com.example.sinep.R
import com.example.sinep.ui.theme.DislikeColor
import com.example.sinep.ui.theme.LikeColor

@Composable
fun PostComment(comment: Comment) {
    var likeClicked by remember {
        mutableStateOf(false)
    }
    var dislikeClicked by remember {
        mutableStateOf(false)
    }

    Column() {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = comment.user.profilePicture),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(42.dp)
                    .clip(CircleShape),
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = comment.user.nickname,
                    style = TextStyle(color = Color.White, fontSize = 15.sp)
                )
                Text(text = comment.content, style = TextStyle(color = Lightgray))
            }
        }
        Row() {
            IconButton(onClick = {
                likeClicked = !likeClicked
                dislikeClicked = false
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.like_img),
                    contentDescription = "like",
                    tint = if (likeClicked) LikeColor else Color.White,
                )
            }
            IconButton(onClick = {
                dislikeClicked = !dislikeClicked
                likeClicked = false
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.dislike_img),
                    contentDescription = "dislike",
                    tint = if (dislikeClicked) DislikeColor else Color.White,
                )
            }
        }
    }
}