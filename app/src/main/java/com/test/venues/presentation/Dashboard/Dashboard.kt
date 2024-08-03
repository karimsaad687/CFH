package com.test.venues.presentation.Dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.test.venues.R
import com.test.venues.presentation.Dashboard.Home.HomeScreen
import com.test.venues.presentation.Dashboard.Home.VenuesViewModel
import com.test.venues.presentation.Dashboard.Profile.ProfileScreen
import com.test.venues.presentation.Dashboard.Terms.TermsScreen
import com.test.venues.presentation.Login
import com.test.venues.presentation.ui.theme.VenuesTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Dashboard : AppCompatActivity() {
    private val venuesViewModel: VenuesViewModel by viewModels()
    private var screenIndex by mutableIntStateOf(1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        venuesViewModel.start(23.0340847, 72.5084728)

        setContent {
            VenuesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    ModalNavigationDrawer(drawerContent = {
                        drawer()
                    }) {
                        when (screenIndex) {
                            1 -> HomeScreen.invoke(modifier = Modifier, state = venuesViewModel.state)
                            2 -> ProfileScreen.invoke(modifier = Modifier, this@Dashboard)
                            3 -> TermsScreen.invoke(modifier = Modifier, this@Dashboard)
                        }
                    }
                }
            }
        }

    }

    @Preview
    @Composable
    fun drawer(modifier: Modifier = Modifier) {
        Box(modifier = modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(1f)
            .background(Color.White)){
            Column(
                modifier
                    .fillMaxSize(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.venue_logo),
                    contentDescription = "",
                    modifier
                        .padding(top = 16.dp)
                        .size(70.dp, 70.dp)
                        .clip(RoundedCornerShape(6.dp))
                )

                drawerItem(icon = Icons.Default.Home, text = "Home", index = 1)
                drawerItem(icon = Icons.Default.Person, text = "Profile", index = 2)
                drawerItem(icon = Icons.Default.Info, text = "Terms & Conditions", index = 3)

            }
            IconButton(
                modifier = Modifier.size(40.dp).align(Alignment.BottomCenter).padding(bottom = 16.dp),
                onClick = {
                    finish()
                    startActivity(Intent(this@Dashboard, Login::class.java))
                }
            ) {
                Icon(
                    Icons.Filled.ExitToApp,
                    "contentDescription",
                )
            }
        }


    }

    @Composable
    fun drawerItem(modifier: Modifier = Modifier, icon: ImageVector, text: String, index: Int) {

        Box(
            modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                .background(Color.Gray, shape = RoundedCornerShape(20.dp))
                .padding(start = 8.dp)
                .clickable {
                    screenIndex = index

                }) {
            Icon(
                imageVector = icon, contentDescription = "",
                modifier
                    .size(20.dp)
                    .align(Alignment.CenterStart)
            )
            Text(
                text,
                modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}