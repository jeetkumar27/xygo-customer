package com.xygo.customer.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {

    var selectedService by remember {
        mutableStateOf("Goods")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B1D3A))
            .verticalScroll(rememberScrollState())
            .padding(
                start = 20.dp,
                top = 50.dp,
                end = 20.dp,
                bottom = 20.dp
            )
    ){
        // Header
        Text(
            text = "Hello!",
            color = Color.LightGray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Where do you want to go?",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Row 1
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            HomeServiceCard(
                title = "Goods",
                subtitle = "Move anything",
                selected = selectedService == "Goods",
                modifier = Modifier.weight(1f),
                onClick = {
                    selectedService = "Goods"
                }
            )

            HomeServiceCard(
                title = "Bike",
                subtitle = "Quick ride",
                selected = selectedService == "Bike",
                modifier = Modifier.weight(1f),
                onClick = {
                    selectedService = "Bike"
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            HomeServiceCard(
                title = "GoRide",
                subtitle = "Everyday ride",
                selected = selectedService == "GoRide",
                modifier = Modifier.weight(1f),
                onClick = {
                    selectedService = "GoRide"
                }
            )

            HomeServiceCard(
                title = "Sedan",
                subtitle = "Comfort ride",
                selected = selectedService == "Sedan",
                modifier = Modifier.weight(1f),
                onClick = {
                    selectedService = "Sedan"
                }
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Pickup
        Text(
            text = "Pickup location",
            color = Color.LightGray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        LocationBox(
            text = "Enter pickup location"
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Drop
        Text(
            text = "Drop location",
            color = Color.LightGray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        LocationBox(
            text = "Enter destination"
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Book button
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .clickable {
                    // Booking logic later
                },
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFC107)
            )
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "BOOK NOW",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun HomeServiceCard(
    title: String,
    subtitle: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(105.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor =
                if (selected) {
                    Color(0xFFFFC107)
                } else {
                    Color(0xFF13294B)
                }
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = title,
                color =
                    if (selected) Color.Black else Color.White,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = subtitle,
                color =
                    if (selected) Color.Black else Color.LightGray,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun LocationBox(
    text: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clickable {
                // Location picker later
            },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF13294B)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = text,
                color = Color.Gray,
                fontSize = 15.sp
            )
        }
    }
}