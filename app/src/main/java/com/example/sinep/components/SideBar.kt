package com.example.sinep.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sinep.R
import com.example.sinep.ui.theme.SideBarColor
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun SideBar() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.5f)
            .background(SideBarColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SideBarRow(imageID = R.drawable.compass, text = "Logos")
            SideBarRow(imageID = R.drawable.compass, text = "Logos")
            SideBarRow(imageID = R.drawable.compass, text = "Logos")
            SideBarRow(imageID = R.drawable.compass, text = "Logos")
//            SideBarRow(imageID = R.drawable.email, text = "Starred")
//            SideBarRow(imageID = R.drawable.plus, text = "Send email")
//            SideBarRow(imageID = R.drawable.email, text = "Drafts")
            Divider(color = Color.Gray, modifier = Modifier.padding(horizontal = 11.dp))
            SideBarRow(imageID = R.drawable.compass, text = "Logos")
            SideBarRow(imageID = R.drawable.compass, text = "Logos")
            SideBarRow(imageID = R.drawable.compass, text = "Logos")

//            SideBarRow(imageID = R.drawable.plus, text = "All Mail")
//            SideBarRow(imageID = R.drawable.email, text = "Trash")
//            SideBarRow(imageID = R.drawable.plus, text = "Spam")
        }
    }
}

@Composable
fun SideBarRow(imageID: Int, text: String) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.clickable { Toast.makeText(context, text, Toast.LENGTH_SHORT).show() }
    ) {

        Icon(
            painter = painterResource(id = imageID),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )

        Text(text = text, style = TextStyle(color = Color.White, fontSize = 20.sp))
    }
}