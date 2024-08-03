package com.test.venues.presentation.Dashboard.Profile

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.venues.common.SharedPref.Companion.getAge
import com.test.venues.common.SharedPref.Companion.getEmail
import com.test.venues.common.SharedPref.Companion.getName
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ProfileScreen {

    companion object Test {
        @Composable
        operator fun invoke(modifier: Modifier, context: Context) {
            Column(
                modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Box(
                    modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = "Profile",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        modifier = modifier.align(Alignment.Center)
                    )
                }

                Divider(thickness = 1.dp, color = Color.Gray)

                Row(modifier.padding(top = 16.dp)) {
                    Text(text = "Name: ", modifier.padding(start = 8.dp), color = Color.DarkGray)
                    Text(text = getName(context)!!)
                }

                Row(modifier.padding(top = 16.dp)) {
                    Text(text = "Email: ", modifier.padding(start = 8.dp), color = Color.DarkGray)
                    Text(text = getEmail(context)!!)
                }

                Row(modifier.padding(top = 16.dp)) {
                    Text(text = "Age: ", modifier.padding(start = 8.dp), color = Color.DarkGray)
                    Text(text = getAge(context)!!)
                }

            }
        }
    }
}