package com.xygo.customer.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController

@Composable
fun PickupLocationScreen(
    navController: NavController
) {
    val context = LocalContext.current

    var searchText by remember {
        mutableStateOf("")
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            val fineGranted =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true

            val coarseGranted =
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            if (fineGranted || coarseGranted) {
                getCurrentLocation(context)
            } else {
                Toast.makeText(
                    context,
                    "Location permission is required",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B1D3A))
            .padding(
                start = 20.dp,
                top = 44.dp,
                end = 20.dp,
                bottom = 20.dp
            )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "‹",
                color = Color.White,
                fontSize = 38.sp,
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Spacer(modifier = Modifier.height(1.dp))

            Text(
                text = "Pickup location",
                color = Color.White,
                fontSize = 25.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Search for a location")
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .clickable {

                    val fineGranted =
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED

                    val coarseGranted =
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED

                    if (fineGranted || coarseGranted) {
                        getCurrentLocation(context)
                    } else {
                        permissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    }
                },
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF13294B)
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Use current location",
                    color = Color(0xFFFFC107),
                    fontSize = 17.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Use your phone's current location",
                    color = Color.LightGray,
                    fontSize = 13.sp
                )
            }
        }
    }
}

private fun getCurrentLocation(
    context: Context
) {

    val locationManager =
        context.getSystemService(
            Context.LOCATION_SERVICE
        ) as LocationManager

    val fineGranted =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    val coarseGranted =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    if (!fineGranted && !coarseGranted) {
        Toast.makeText(
            context,
            "Location permission not granted",
            Toast.LENGTH_LONG
        ).show()

        return
    }

    val provider =
        when {
            locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            ) -> LocationManager.GPS_PROVIDER

            locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            ) -> LocationManager.NETWORK_PROVIDER

            else -> null
        }

    if (provider == null) {
        Toast.makeText(
            context,
            "Please turn on location services",
            Toast.LENGTH_LONG
        ).show()

        return
    }

    val location =
        locationManager.getLastKnownLocation(provider)

    if (location != null) {

        Toast.makeText(
            context,
            "Location found: ${location.latitude}, ${location.longitude}",
            Toast.LENGTH_LONG
        ).show()

    } else {

        Toast.makeText(
            context,
            "Unable to get your location yet",
            Toast.LENGTH_LONG
        ).show()
    }
}