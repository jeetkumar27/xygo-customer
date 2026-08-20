package com.xygo.customer.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.xygo.customer.R

@Composable
fun LoginScreen(navController: NavController) {

    var phoneNumber by remember {
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

        Image(
            painter = painterResource(R.drawable.xygo_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(0.85f),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Move Anything.\nAnytime.",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = phoneNumber,

            onValueChange = { value ->

                val filtered = value.filter { it.isDigit() }

                if (filtered.length <= 10) {
                    phoneNumber = filtered
                }
            },

            label = {
                Text("Mobile Number")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),

            singleLine = true,

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,

                cursorColor = Color(0xFFFFC107),

                focusedBorderColor = Color(0xFFFFC107),
                unfocusedBorderColor = Color.Gray,

                focusedLabelColor = Color(0xFFFFC107),
                unfocusedLabelColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate("home")
            },

            enabled = phoneNumber.length == 10,

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor =
                    if (phoneNumber.length == 10)
                        Color(0xFFFFC107)
                    else
                        Color.DarkGray
            ),

            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "CONTINUE",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "By continuing, you agree to our\nTerms & Conditions and Privacy Policy",
            color = Color.LightGray,
            fontSize = 13.sp
        )
    }
}