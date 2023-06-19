package com.example.sinep

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.sinep.ui.theme.LinkText
import com.example.sinep.ui.theme.LoginBG
import com.example.sinep.ui.theme.LoginButton
import com.example.sinep.ui.theme.LoginCardBD

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Login()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun Login() {
        val context = this@MainActivity
        var loginState by remember { mutableStateOf("") }
        var passwordState by remember { mutableStateOf("") }
        var checkFields by remember { mutableStateOf(false) }
        val focusManager = LocalFocusManager.current

        val coroutineScope = rememberCoroutineScope()
        val bringIntoViewRequester = BringIntoViewRequester()
        val roundedCornerShape = RoundedCornerShape(
            topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp
        )

        Box(
            modifier = Modifier
                .background(LoginBG)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .background(LoginCardBD, shape = roundedCornerShape)
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.95f)
                    .clip(shape = roundedCornerShape),

                ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "logo",
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .aspectRatio(1f)
                        )
                        TextField(value = loginState,
                            onValueChange = {
                                loginState = it
                                checkFields = false
                            },
                            label = { Text("Login or email") },
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .clip(RoundedCornerShape(30.dp)),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done, keyboardType = KeyboardType.Email
                            ),
                            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                            trailingIcon = {
                                Icon(
                                    painterResource(id = R.drawable.warning_img),
                                    "",
                                    tint = Color.Red,
                                    modifier = Modifier.alpha(if (checkFields && loginState == "") 1f else 0f)
                                )
                            })
                        TextField(value = passwordState,
                            onValueChange = {
                                passwordState = it
                                checkFields = false
                            },
                            label = { Text("Password") },
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .padding(top = 15.dp, bottom = 50.dp)
                                .clip(RoundedCornerShape(30.dp)),
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                            ),
                            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                            trailingIcon = {
                                Icon(
                                    painterResource(id = R.drawable.warning_img),
                                    "",
                                    tint = Color.Red,
                                    modifier = Modifier.alpha(if (checkFields && passwordState == "") 1f else 0f)
                                )
                            })
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = {
                                val values = arrayOf(loginState, passwordState)
                                if ("" in values)
                                    checkFields = true
                                else {
                                    val intent = Intent(context, MainPage::class.java).apply { putExtra("login", loginState) }
                                    context.startActivity(intent)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(LoginButton),
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .height(53.dp)
                                .clip(RoundedCornerShape(60.dp))
                        ) {
                            Text(
                                text = "Log In",
                                style = TextStyle(color = Color.White, fontSize = 20.sp)
                            )
                        }
                        Button(
                            onClick = {
                                Toast.makeText(
                                    context, "$loginState $passwordState", Toast.LENGTH_SHORT
                                ).show()
                            },
                            colors = ButtonDefaults.buttonColors(LoginButton),
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .clip(RoundedCornerShape(60.dp))
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Log In with Google",
                                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.google_logo),
                                    contentDescription = "Google logo",
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(40.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            Text(
                                text = " Don't have an account?",
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp)
                            )
                            ClickableText(
                                text = AnnotatedString("Sign-in now!"), onClick = {
                                    val intent = Intent(context, Registration::class.java)
                                    context.startActivity(intent)
                                }, style = TextStyle(
                                    color = LinkText,
                                    textDecoration = TextDecoration.Underline,
                                    fontSize = 17.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

