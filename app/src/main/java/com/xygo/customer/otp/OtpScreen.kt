package com.xygo.customer.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.ui.unit.sp

@Composable
fun OtpScreen(phoneNumber: String) {

    var otp by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B1D3A))
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Enter OTP",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Enter the OTP sent to $phoneNumber",
            color = Color.White,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = {
                val filtered = it.filter { ch -> ch.isDigit() }

                if (filtered.length <= 6) {
                    otp = filtered
                }
            },
            label = {
                Text("OTP")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // OTP verification will go here later
            },

            enabled = otp.length == 6,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {
            Text(
                text = "VERIFY",
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Resend OTP",
            color = Color(0xFFFFC107),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                // Resend OTP will be implemented later
            }
        )
    }
}