package com.example.sinep

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.sinep.components.ArrayDropDown
import com.example.sinep.components.RegistrationField
import com.example.sinep.ui.theme.LoginBG
import com.example.sinep.ui.theme.LoginCardBD
import com.example.sinep.ui.theme.SignUpBTN

class Registration : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistrationComposable()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun RegistrationComposable() {
        val firstNameState = remember { mutableStateOf("") }
        val lastNameState = remember { mutableStateOf("") }
        val loginState = remember { mutableStateOf("") }
        val passwordState = remember { mutableStateOf("") }
        val ageState = remember { mutableStateOf(0) }
        val genderState = remember { mutableStateOf("") }
        val cityState = remember { mutableStateOf("") }
        val phoneState = remember { mutableStateOf("") }
        val emailState = remember { mutableStateOf("") }

        val focusManager = LocalFocusManager.current

        val checkFields = remember { mutableStateOf(false) }

        val keyboardController = LocalSoftwareKeyboardController.current

        fun getAgeValue(): String = if (ageState.value == 0) "" else ageState.value.toString()

        fun setAgeValue(ageValue: String) {
            ageState.value = if (ageValue == "") 0 else ageValue.toInt()
        }

        val roundedCornerShape = RoundedCornerShape(
            topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp
        )
        val context = this@Registration
        Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LoginBG)
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .clip(roundedCornerShape)
                        .background(LoginCardBD, shape = roundedCornerShape)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "logo",
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                                .aspectRatio(1f)
                        )
                        Column(modifier = Modifier.fillMaxWidth(0.9f)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 50.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(modifier = Modifier.weight(0.4f)) {
                                    RegistrationField(
                                        state = firstNameState,
                                        checkFields = checkFields,
                                        focusManager = focusManager,
                                        labelText = "Name",
                                        keyboardType = KeyboardType.Text
                                    )
                                }
                                Box(modifier = Modifier.weight(0.1f))
                                Box(modifier = Modifier.weight(0.4f)) {
                                    RegistrationField(
                                        state = lastNameState,
                                        checkFields = checkFields,
                                        focusManager = focusManager,
                                        labelText = "Surname",
                                        keyboardType = KeyboardType.Text
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                RegistrationField(
                                    state = loginState,
                                    checkFields = checkFields,
                                    focusManager = focusManager,
                                    labelText = "Login",
                                    keyboardType = KeyboardType.Text
                                )
                                RegistrationField(
                                    state = passwordState,
                                    checkFields = checkFields,
                                    focusManager = focusManager,
                                    labelText = "Password",
                                    keyboardType = KeyboardType.Password,
                                    isPassword = true
                                )
                                TextField(value = getAgeValue(),
                                    onValueChange = {
                                        setAgeValue(it)
                                        checkFields.value = false
                                    },
                                    label = { Text("Age") },
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(30.dp))
                                        .fillMaxWidth(),
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Done,
                                        keyboardType = KeyboardType.Number
                                    ),
                                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                                    trailingIcon = {
                                        Icon(
                                            painterResource(id = R.drawable.warning_img),
                                            "",
                                            tint = Color.Red,
                                            modifier = Modifier.alpha(if (checkFields.value && getAgeValue() == "") 1f else 0f)
                                        )
                                    })
                                ArrayDropDown(
                                    state = genderState,
                                    options = arrayOf("Male"),
                                    checkFields = checkFields
                                )
                                RegistrationField(
                                    state = cityState,
                                    checkFields = checkFields,
                                    focusManager = focusManager,
                                    labelText = "City",
                                    keyboardType = KeyboardType.Text
                                )
                                RegistrationField(
                                    state = phoneState,
                                    checkFields = checkFields,
                                    focusManager = focusManager,
                                    labelText = "Phone number",
                                    keyboardType = KeyboardType.Phone
                                )
                                RegistrationField(
                                    state = emailState,
                                    checkFields = checkFields,
                                    focusManager = focusManager,
                                    labelText = "Email",
                                    keyboardType = KeyboardType.Email
                                )
                            }
                            Button(
                                onClick = {
                                    val fieldValues = arrayOf(
                                        firstNameState.value,
                                        lastNameState.value,
                                        loginState.value,
                                        passwordState.value,
                                        getAgeValue(),
                                        genderState.value,
                                        cityState.value,
                                        phoneState.value,
                                        emailState.value
                                    )
                                    if ("" in fieldValues) {
                                        keyboardController?.hide()
                                        Toast.makeText(
                                            context,
                                            "You haven't filled in all the fields.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        checkFields.value = true
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Congratulations! You are a SinePee now!",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        context.startActivity(Intent(context, MainPage::class.java))
                                    }
                                },
                                modifier = Modifier
                                    .padding(top = 60.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(60.dp))
                                    .height(53.dp),
                                colors = ButtonDefaults.buttonColors(SignUpBTN),
                            ) {
                                Text(
                                    text = "Sign Up", style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp,
                                        color = Color.White
                                    )
                                )
                            }
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(60.dp))
                                    .height(53.dp),
                                colors = ButtonDefaults.buttonColors(SignUpBTN),
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row {}
                                    Text(
                                        text = "Sign Up with Google",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 17.sp,
                                            color = Color.White
                                        ),
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
                        }
                    }
                }
            }
        }
    }
}