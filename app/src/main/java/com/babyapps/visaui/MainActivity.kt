package com.babyapps.visaui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.babyapps.visaui.ui.theme.CoolBlue
import com.babyapps.visaui.ui.theme.DefaultTheme
import com.babyapps.visaui.ui.theme.NiceBlue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultTheme {
                SignInScreen()
            }
        }
    }
}

@Composable
fun SignInScreen() {
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    val isValidInfo by derivedStateOf {
        username.isNotEmpty() && password.length>8
    }



    Scaffold(backgroundColor = NiceBlue) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_visa),
                contentDescription = "Visa",
                modifier = Modifier
                    .weight(1f)
                    .size(200.dp),
                //We can paint Visa icon
                colorFilter = ColorFilter.tint(Color.Yellow)
            )

            Card(
                shape = RoundedCornerShape(35.dp),
                modifier = Modifier
                    .weight(2f)
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(35.dp)
                ) {
                    Text(
                        text = "Card Informations",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer( modifier= Modifier.weight(1f))
                        OutlinedTextField(
                            value = username,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                username = it
                            },
                            label = { Text(text = "Username") },
                            singleLine = true,
                            trailingIcon = {
                                IconButton(onClick = { username = "" }) {
                                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                //We show Done icon in the keyboard
                                imeAction = ImeAction.Done
                            ),
                            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            value = password,
                            //It would be taller if we use fillmaxsize
                            //modifier = Modifier.fillMaxSize(),
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                password = it
                            },
                            label = { Text(text = "Password") },

                            trailingIcon = {
                                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                    //Eye Icons :)))
                                    Icon(
                                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = "Password Toggle"
                                    )
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = CoolBlue),
                            onClick = { /*TODO*/ },
                            enabled = isValidInfo,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(text = "Log In", color = Color.White, fontSize = 20.sp)
                        }

                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(onClick = { }) {
                                Text(text = "Sign Up", color = Color.DarkGray, fontSize = 16.sp)
                            }
                            TextButton(onClick = { }) {
                                Text(
                                    text = "Forgot Password?",
                                    color = Color.DarkGray,
                                    fontSize = 16.sp
                                )
                            }
                        }

                    }
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DefaultTheme {
        SignInScreen()
    }
}