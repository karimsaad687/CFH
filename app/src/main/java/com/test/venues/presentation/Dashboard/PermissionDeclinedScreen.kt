package com.test.venues.presentation.Dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PermissionDeclinedScreen {

    companion object Test {
        @Composable
        operator fun invoke(modifier: Modifier, dashboard: Dashboard) {

            Box(
                modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier
                        .align(Alignment.Center)
                        .clickable {
                            dashboard.checkLocationPermission()
                        }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        modifier.size(100.dp, 100.dp)
                    )
                    Text(
                        text = "Grant Permission",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        modifier = modifier.padding(top = 16.dp)
                    )
                }

            }
        }
    }
}