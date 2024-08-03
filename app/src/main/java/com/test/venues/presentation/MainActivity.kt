package com.test.venues.presentation

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.test.venues.R
import com.test.venues.common.SharedPref
import com.test.venues.presentation.Dashboard.Dashboard
import com.test.venues.presentation.ui.theme.VenuesTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {
            VenuesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Greeting()
                }
            }
        }
    }

    private fun openNewActivity() {
        if(SharedPref.isLogin(this)){
            startActivity(Intent(this, Dashboard::class.java))
        }else {
            startActivity(Intent(this, Login::class.java))
        }
        finish()
    }


    @Composable
    fun Greeting(modifier: Modifier = Modifier) {


        var imageVisible by remember { mutableStateOf(false) }

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = imageVisible,
                enter = fadeIn(animationSpec = tween(2000))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.venue_logo),
                    contentDescription = "",
                    modifier.fillMaxSize(0.6f)
                )
            }
        }

        LaunchedEffect(Unit) {
            delay(100)
            imageVisible = true
            delay(2000)
            openNewActivity()
        }
    }

}