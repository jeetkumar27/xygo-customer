package com.xygo.customer.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpScreen(phoneNumber: String) {

    var otp by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B1D3A)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Enter OTP",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Enter the OTP sent to",
            color = Color.LightGray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = phoneNumber,
            color = Color(0xFFFFC107),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(35.dp))

        BasicTextField(
            value = otp,
            onValueChange = { value ->

                val filtered = value.filter { it.isDigit() }

                if (filtered.length <= 6) {
                    otp = filtered
                }
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            decorationBox = {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    repeat(6) { index ->

                        val digit =
                            if (index < otp.length) {
                                otp[index].toString()
                            } else {
                                ""
                            }

                        val isActive = index == otp.length

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .border(
                                    width = if (isActive) 2.dp else 1.dp,
                                    color = if (isActive)
                                        Color(0xFFFFC107)
                                    else
                                        Color.Gray,
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = digit,
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                // OTP verification will go here later
            },

            enabled = otp.length == 6,

            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(55.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC107),
                disabledContainerColor = Color.DarkGray
            ),

            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "VERIFY",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Resend OTP",
            color = Color(0xFFFFC107),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}-