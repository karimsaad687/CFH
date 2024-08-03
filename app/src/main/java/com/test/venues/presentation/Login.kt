package com.test.venues.presentation

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.lifecycleScope
import com.test.venues.R
import com.test.venues.Room.AppDatabase
import com.test.venues.Room.User
import com.test.venues.common.SharedPref.Companion.saveAge
import com.test.venues.common.SharedPref.Companion.saveEmail
import com.test.venues.common.SharedPref.Companion.saveName
import com.test.venues.common.SharedPref.Companion.setIsLogin
import com.test.venues.common.Utils.Companion.isValidEmail
import com.test.venues.presentation.Dashboard.Dashboard
import com.test.venues.presentation.ui.theme.VenuesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Login : ComponentActivity() {
    var isLogin by mutableStateOf(true)
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDatabase(this)
        val regex =
            Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$")
        Log.i("datadata", "1234567".matches(regex).toString())
        Log.i("datadata", "12345678@".matches(regex).toString())
        Log.i("datadata", "12345678@k".matches(regex).toString())
        Log.i("datadata", "1234567@k".matches(regex).toString())
        Log.i("datadata", "karim12@".matches(regex).toString())
        setContent {
            VenuesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    auth()
                }
            }
        }
    }

    @Preview
    @Composable
    fun auth(modifier: Modifier = Modifier) {
        Column(
            modifier
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(R.drawable.venue_logo), contentDescription = "")

            if (isLogin) {
                loginUI(modifier)
            } else {
                registerUI(modifier)
            }
        }

    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun loginUI(modifier: Modifier = Modifier) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier
                .fillMaxWidth(0.9f)
                .clip(shape = RoundedCornerShape(Dp(10f)))
                .background(Color.White),
            placeholder = {
                Text(text = "Email", color = Color.Gray)
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),

            maxLines = 1
        )
        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier
                .padding(top = Dp(16f))
                .fillMaxWidth(0.9f)
                .clip(shape = RoundedCornerShape(Dp(10f)))
                .background(Color.White),
            placeholder = {
                Text(text = "Password", color = Color.Gray)
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ), maxLines = 1
        )
        OutlinedButton(
            onClick = {

                    if (email.isEmpty()) {
                        Toast.makeText(
                            this@Login,
                            "Email field is empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (!isValidEmail(email)) {
                        Toast.makeText(
                            this@Login,
                            "Please write a valid email",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (password.isEmpty()) {
                        Toast.makeText(
                            this@Login,
                            "Password field is empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        lifecycleScope.launch(Dispatchers.IO) {
                            val user = db.userDao().findByEmailPassword(email, password)
                            if (user != null) {
                                saveName(this@Login, user.first + " " + user.last)
                                saveEmail(this@Login, user.email)
                                saveAge(this@Login, user.age)
                                setIsLogin(this@Login,true)
                                startActivity(Intent(this@Login, Dashboard::class.java))
                            } else {
                                lifecycleScope.launch(Dispatchers.Main) {
                                    Toast.makeText(
                                        this@Login,
                                        "Wrong email or password",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }

            },
            modifier
                .clip(shape = RoundedCornerShape(Dp(10f)))
                .padding(top = Dp(16f))
                .fillMaxWidth(0.9f)
                .height(40.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.White), border = BorderStroke(0.dp, Color.Transparent)
        ) {
            Text(text = "Login")
        }
        Text(
            text = "Create account",
            modifier
                .padding(top = 8.dp)
                .clickable {
                    isLogin = false
                },
            color = Color.White
        )
    }

    @Composable
    fun registerUI(modifier: Modifier = Modifier) {
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        TextField(
            value = firstName,
            onValueChange = {
                firstName = it
            },
            modifier
                .fillMaxWidth(0.9f)
                .clip(shape = RoundedCornerShape(Dp(10f)))
                .background(Color.White),
            placeholder = {
                Text(text = "First name", color = Color.Gray)
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            maxLines = 1
        )

        TextField(
            value = lastName,
            onValueChange = {
                lastName = it
            },
            modifier
                .padding(top = 16.dp)
                .fillMaxWidth(0.9f)
                .clip(shape = RoundedCornerShape(Dp(10f)))
                .background(Color.White),
            placeholder = {
                Text(text = "Last name", color = Color.Gray)
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            maxLines = 1
        )

        TextField(
            value = age,
            onValueChange = {
                if (it.isDigitsOnly()) age = it

            },
            modifier
                .padding(top = 16.dp)
                .fillMaxWidth(0.9f)
                .clip(shape = RoundedCornerShape(Dp(10f)))
                .background(Color.White),
            placeholder = {
                Text(text = "Age", color = Color.Gray)
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            maxLines = 1
        )


        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier
                .padding(top = 16.dp)
                .fillMaxWidth(0.9f)
                .clip(shape = RoundedCornerShape(Dp(10f)))
                .background(Color.White),
            placeholder = {
                Text(text = "Email", color = Color.Gray)
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            maxLines = 1
        )

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier
                .padding(top = Dp(16f))
                .fillMaxWidth(0.9f)
                .clip(shape = RoundedCornerShape(Dp(10f)))
                .background(Color.White),
            placeholder = {
                Text(text = "Password", color = Color.Gray)
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            maxLines = 1
        )
        OutlinedButton(
            onClick = {
                if (firstName.isEmpty()) {
                    Toast.makeText(
                        this@Login,
                        "First name field is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (lastName.isEmpty()) {
                    Toast.makeText(
                        this@Login,
                        "Last name field is empty",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                } else if (age.isEmpty()) {
                    Toast.makeText(
                        this@Login,
                        "Age field is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (email.isEmpty()) {
                    Toast.makeText(
                        this@Login,
                        "Email field is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!isValidEmail(email)) {
                    Toast.makeText(
                        this@Login,
                        "Please write a valid email",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (password.isEmpty()) {
                    Toast.makeText(
                        this@Login,
                        "Paswword field is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!password.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$"))) {
                    Toast.makeText(
                        this@Login,
                        "Password should be 8 characters long with Alphanumeric and Special\n" +
                                "Characters",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    lifecycleScope.launch(Dispatchers.IO) {
                        db.userDao().insertUser(
                            User(
                                first = firstName,
                                last = lastName,
                                age = age,
                                email = email,
                                password = password
                            )
                        )
                        isLogin = true

                    }
                }

            },
            modifier
                .clip(shape = RoundedCornerShape(Dp(10f)))
                .padding(top = Dp(16f))
                .fillMaxWidth(0.9f)
                .height(40.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.White), border = BorderStroke(0.dp, Color.Transparent)
        ) {
            Text(text = "Create account")
        }
        Text(
            text = "Login",
            modifier
                .padding(top = 8.dp)
                .clickable {
                    isLogin = true
                },
            color = Color.White
        )
    }
}