package com.example.sinep.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sinep.R
import com.example.sinep.models.Comment
import com.example.sinep.models.Post

@Composable
fun UserInfoScore(commentsNumber: Int, comment: Comment, postsNumber: Int, post: Post) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.padding(bottom = 10.dp, start = 20.dp)) {
            Row(
                modifier = Modifier.padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.comment_img),
                    contentDescription = "comment_img",
                    tint = Color.White
                )
                Text(text = "Comments: $commentsNumber", color = Color.White)
            }
            Text(text = "Most liked comment:", color = Color.White)
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp), color = Color.Gray
        )
        UserInfoComment(comment = comment)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp), color = Color.Gray
        )
        Column(modifier = Modifier.padding(bottom = 10.dp, start = 20.dp)) {
            Row(
                modifier = Modifier.padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.post),
                    tint = Color.White,
                    contentDescription = "post_img"
                )
                Text(text = "Posts: $postsNumber", color = Color.White)
            }
            Text(text = "Most liked post:", color = Color.White)
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp), color = Color.Gray
        )
        UserInfoPost(post = post)
    }
}