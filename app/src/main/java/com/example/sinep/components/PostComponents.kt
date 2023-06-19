package com.example.sinep.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SwipeableState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sinep.R
import com.example.sinep.models.Post
import com.example.sinep.ui.theme.*
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostComponents(
    post: Post,
    navControler: NavController,
    swipeableState: SwipeableState<Int>,
    commentsCount: Int = 0
) {
    val text = post.title
    val user = post.user
    val imageID = post.image
    var isMultipleLines by remember { mutableStateOf(false) }
    var moreVisible by remember { mutableStateOf(false) }
    var likeClicked by remember { mutableStateOf(false) }
    var dislikeClicked by remember { mutableStateOf(false) }
    var lineLength by remember { mutableStateOf(0) }
    var textState by remember { mutableStateOf(text) }

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
                    painter = painterResource(id = user.profilePicture),
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
                        text = user.nickname,
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
                color = Color.White,
                fontSize = 20.sp
            ),
        )
        Image(
            painter = painterResource(id = imageID),
            contentDescription = "Post image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(20.dp))
                .clickable(enabled = swipeableState.offset.value.roundToInt() == 0, onClick = {
                    navControler.currentBackStackEntry?.savedStateHandle?.set(
                        key = "post",
                        value = post
                    )
                    navControler.navigate(route = "post_detail")
                })
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(bottom = 10.dp, start = 5.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.like_img),
                contentDescription = "Like",
                tint = if (likeClicked) LikeColor else Color.White,
                modifier = Modifier.clickable { likeClicked = !likeClicked; dislikeClicked = false }
            )
            Icon(
                painter = painterResource(id = R.drawable.dislike_img),
                contentDescription = "Dislike",
                tint = if (dislikeClicked) DislikeColor else Color.White,
                modifier = Modifier.clickable {
                    likeClicked = false; dislikeClicked = !dislikeClicked
                }
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = commentsCount.toString(), style = TextStyle(Color.White))
                Icon(
                    painter = painterResource(id = R.drawable.comment_img),
                    contentDescription = "Comment",
                    tint = TaintWhite,
                )
            }
        }
    }
}
