package com.test.venues.presentation.Dashboard.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.test.venues.R
import com.test.venues.data.dto.toList
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HomeScreen {

    companion object Test {
        @Composable
        operator fun invoke(modifier: Modifier, state: State<VenuesState>, location: String) {
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
                        text = "Home",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        modifier = modifier.align(Alignment.Center)
                    )
                }

                Divider(thickness = 1.dp, color = Color.Gray)

                if (state.value.data == null) {
                    Box(modifier = modifier.fillMaxSize()) {
                        Text(
                            text = "Fetching data and location...",
                            modifier.align(Alignment.Center),
                            color = Color.DarkGray,
                            fontSize = 17.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Black
                        )
                    }
                } else if (state.value.data != null) {
                    LazyColumn(content = {
                        val list = state.value.data!!.toList()
                        items(list.size) {
                            val item = list.get(it)
                            var image = ""
                            if (item.categories.size > 0) {
                                val icon = item.categories.get(0).icon
                                image = icon.prefix + icon.suffix
                            }
                            Column(
                                modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .border(
                                        1.dp,
                                        color = Color.Cyan,
                                        shape = RoundedCornerShape(14.dp)
                                    )
                                    .clip(RoundedCornerShape(14.dp))

                            ) {
                                AsyncImage(
                                    model = image, contentDescription = null,
                                    modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .padding(top = 8.dp),
                                    placeholder = painterResource(R.drawable.no_category),
                                    error = painterResource(R.drawable.no_category),

                                    )
                                Row {
                                    Text(
                                        text = "Name: ",
                                        modifier.padding(start = 8.dp),
                                        color = Color.DarkGray,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(text = item.name)
                                }

                                Row {
                                    Text(
                                        text = "Location: ",
                                        modifier.padding(start = 8.dp),
                                        color = Color.DarkGray,
                                        fontWeight = FontWeight.SemiBold
                                    )

                                    Text(text = item.location.address?:"No address")
                                }

                                Row(modifier.padding(bottom = 8.dp)) {
                                    Text(
                                        text = "Category: ",
                                        modifier.padding(start = 8.dp),
                                        color = Color.DarkGray,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = if (item.categories.isNotEmpty()) item.categories.get(
                                            0
                                        ).name else "no category specified"
                                    )
                                }

                            }
                        }

                    })
                }

            }
        }
    }
}