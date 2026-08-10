package com.xygo.customer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xygo.customer.splash.LoginScreen
import com.xygo.customer.otp.OtpScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(navController)
        }
        composable(
            route = "otp/{phoneNumber}",
            arguments = listOf(
                navArgument("phoneNumber") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""

            OtpScreen(phoneNumber = phoneNumber)
        }
    }
}