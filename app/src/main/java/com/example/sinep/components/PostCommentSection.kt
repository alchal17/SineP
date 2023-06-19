package com.example.sinep.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.sinep.models.Comment

@Composable
fun PostCommentSection(comments: Array<Comment>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Comments:", color = Color.White)
        LazyColumn() {
            items(comments){comment: Comment ->  
                PostComment(comment = comment)
            }
        }
    }
}