package com.example.sinep.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sinep.R
import com.example.sinep.ui.theme.LoginButton
import com.example.sinep.components.VideoView
import java.net.URI

data class AddBarItem(
    val name: String, val icon_id: Int, val onItemClick: () -> Unit
)


@Composable
fun AddPost() {
    val focusManager = LocalFocusManager.current
    var title by remember {
        mutableStateOf("")
    }
    var body by remember {
        mutableStateOf("")
    }
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                selectedImageUri = uri
            }
        )
    var selectedVideoUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val singleVideoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                selectedVideoUri = uri
            })

    val barElements =
        listOf(
            AddBarItem("link", R.drawable.link, {}), AddBarItem("image", R.drawable.image, {
                selectedVideoUri = null
                singlePhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }),
            AddBarItem("video", R.drawable.video, {
                selectedImageUri = null
                selectedVideoUri = null
                singleVideoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
            })
        )
    val customTextFieldColors = TextFieldDefaults.textFieldColors(
        disabledTextColor = Color.Transparent,
        backgroundColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        cursorColor = Color.Blue,
        textColor = Color.White
    )
    Scaffold(
        bottomBar = {
            AddPostBar(elements = barElements)
        }, backgroundColor = Color.Black
    ) { inner_padding ->
        Box(modifier = Modifier.padding(bottom = inner_padding.calculateBottomPadding())) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = title,
                        onValueChange = {
                            title = it
                        },
                        label = {
                            Text(
                                text = "TITLE",
                                style = TextStyle(color = Color.Gray),
                                modifier = Modifier.weight(1f)
                            )
                        },
                        colors = customTextFieldColors,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(horizontal = 8.dp),
                        colors = ButtonDefaults.buttonColors(LoginButton)
                    ) {
                        Text(text = "OK", style = TextStyle(color = Color.White))
                    }
                }
                TextField(value = body, onValueChange = {
                    body = it
                }, label = {
                    Text(
                        text = "body text (optional)",
                        style = TextStyle(color = Color.Gray),
                        modifier = Modifier.weight(1f)
                    )
                }, colors = customTextFieldColors, keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                ), modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                )
                Box(
                    modifier = Modifier
                        .padding(
                            start = 7.dp,
                            end = 7.dp,
                            bottom = 7.dp
                        )
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedImageUri != null) {
                        AsyncImage(
                            model = selectedImageUri,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(25.dp)),
                        )
                        IconButton(
                            onClick = {
                                selectedImageUri = null
                            },
                            modifier = Modifier
                                .fillMaxSize(0.1f)
                                .align(
                                    Alignment.TopEnd
                                )
                                .padding(3.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cancel),
                                contentDescription = "cancel",
                                tint = Color.White
                            )
                        }
                    } else if (selectedVideoUri != null) {
                        VideoView(videoUri = selectedVideoUri.toString())
                        IconButton(
                            onClick = {
                                selectedVideoUri = null
                            },
                            modifier = Modifier
                                .fillMaxSize(0.1f)
                                .align(
                                    Alignment.TopEnd
                                )
                                .padding(3.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cancel),
                                contentDescription = "cancel",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AddPostBar(elements: List<AddBarItem>) {
    Row(
        modifier = Modifier
            .background(color = Color.DarkGray)
            .fillMaxWidth()
    ) {
        elements.forEach { element ->
            IconButton(onClick = { element.onItemClick() }) {
                Icon(
                    painter = painterResource(id = element.icon_id),
                    contentDescription = element.name,
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }
        }
    }
}