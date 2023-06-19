package com.example.sinep.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sinep.R
import com.example.sinep.models.Comment
import com.example.sinep.models.Post
import com.example.sinep.models.User
import com.example.sinep.ui.theme.DislikeColor
import com.example.sinep.ui.theme.Lightgray
import com.example.sinep.ui.theme.LikeColor
import com.example.sinep.ui.theme.PostBG
import com.example.sinep.ui.theme.TaintWhite
import com.example.sinep.components.PostCommentSection

@Composable
fun PostDetail(post: Post) {
    val user1 = User("123", R.drawable.wb)
    val user2 = User("123", R.drawable.wb)
    val user3 = User("123", R.drawable.wb)
    val user4 = User("123", R.drawable.wb)

    val comments = arrayOf(
        Comment("aaaaaaaaaaa", post, user1),
        Comment("aaaaaaaaaaa уостустук wjnwdnewdnewfne eifewifnewinfew iefewincewcn", post, user2),
        Comment("aaaaaaaaaaa", post, user3),
        Comment("aaaaaaaaaaa", post, user4)
    )
    var textState by remember { mutableStateOf(post.title) }
    var isMultipleLines by remember { mutableStateOf(false) }
    var moreVisible by remember { mutableStateOf(false) }
    var likeClicked by remember { mutableStateOf(false) }
    var dislikeClicked by remember { mutableStateOf(false) }
    var lineLength by remember { mutableStateOf(0) }
    var commentsExtended by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(PostBG)
            .padding(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = post.user.profilePicture),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(62.dp)
                        .clip(CircleShape),
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = 10.dp)
                ) {
                    Text(
                        text = post.user.nickname,
                        style = TextStyle(color = Color.White, fontSize = 15.sp)
                    )
                    Text(text = "s/Username", style = TextStyle(color = Lightgray))
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.more_img),
                contentDescription = "More...",
                tint = Color.White,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Text(
            text = textState,
            onTextLayout = { textLayoutResult: TextLayoutResult ->
                isMultipleLines = textLayoutResult.lineCount > 1
                if (isMultipleLines) {
                    moreVisible = true
                    lineLength = textLayoutResult.getLineEnd(0) - textLayoutResult.getLineStart(0)
                    textState = textState.slice(0 until lineLength - 3) + "..."
                }
            },
            style = TextStyle(
                color = Color.White, fontSize = 20.sp
            ),
        )
        Image(
            painter = painterResource(id = post.image),
            contentDescription = "Post image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(20.dp))
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(bottom = 10.dp, start = 5.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.like_img),
                contentDescription = "Like",
                tint = if (likeClicked) LikeColor else Color.White,
                modifier = Modifier.clickable {
                    likeClicked = !likeClicked; dislikeClicked = false
                })
            Icon(painter = painterResource(id = R.drawable.dislike_img),
                contentDescription = "Dislike",
                tint = if (dislikeClicked) DislikeColor else Color.White,
                modifier = Modifier.clickable {
                    likeClicked = false; dislikeClicked = !dislikeClicked
                })
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.comment_img),
                    contentDescription = "Comment",
                    tint = if (commentsExtended) Color.Gray else TaintWhite,
                    modifier = Modifier.clickable { commentsExtended = !commentsExtended }
                )
            }
        }
        if (commentsExtended) {
            Box(modifier = Modifier.fillMaxWidth()) {
                PostCommentSection(comments = comments)
            }
        } else {
            Text(text = "Description: ${post.description}", style = TextStyle(Color.Gray))
        }
    }
}