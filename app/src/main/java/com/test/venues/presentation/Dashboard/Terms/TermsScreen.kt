package com.test.venues.presentation.Dashboard.Terms

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.venues.R
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class TermsScreen {

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
                        text = "Terms and Condition",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        modifier = modifier.align(Alignment.Center)
                    )
                }

                Divider(thickness = 1.dp, color = Color.Gray)

                Text(
                    text = context.getString(R.string.lorem_ipsum),
                    modifier
                        .padding(all = 16.dp)
                        .verticalScroll(rememberScrollState()),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

            }
        }
    }
}