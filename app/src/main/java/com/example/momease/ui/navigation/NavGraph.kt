package com.example.momease.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.momease.MomeaseApp
import com.example.momease.screens.AboutUsScreen
import com.example.momease.screens.CartItem
import com.example.momease.screens.CartScreen
import com.example.momease.screens.ConfirmOrderScreen
import com.example.momease.screens.GrievancesScreen
import com.example.momease.screens.MyBillsScreen
import com.example.momease.screens.MyQRCodeScreen
import com.example.momease.screens.MyWalletScreen
import com.example.momease.screens.NotificationScreen
import com.example.momease.screens.OrderHistoryScreen
import com.example.momease.screens.ProfileScreen
import com.example.momease.screens.RefundPolicyScreen
import com.example.momease.screens.TermsAndConditionsScreen
import com.example.momease.ui.screens.LoginFormScreen
import com.example.momease.ui.screens.LoginHomeScreen
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        // Splash / Entry screen
        composable("home") {
            LoginHomeScreen(
                onLoginClicked = {
                    navController.navigate("login")
                }
            )
        }

        // Login screen
        composable("login") {
            LoginFormScreen(
                onVerifyOtpClick = {
                    navController.navigate("dashboard") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onGoogleSignIn = {
                    navController.navigate("dashboard") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onFacebookSignIn = {
                    navController.navigate("dashboard") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("profile") {
            ProfileScreen(navController = navController)
        }

        // Main app layout with drawer/navigation
        composable("dashboard") {
            MomeaseApp(navController = navController) // MomeaseApp includes Home, Cart, Drawer, etc.
        }

        // Confirm order screen
        composable(
            route = "confirmOrder/{cartItems}",
            arguments = listOf(navArgument("cartItems") { type = NavType.StringType })
        ) { backStackEntry ->
            val cartItemsJson = backStackEntry.arguments?.getString("cartItems") ?: ""
            val decodedJson = URLDecoder.decode(cartItemsJson, StandardCharsets.UTF_8.toString())
            val cartItems = try {
                Json.decodeFromString<List<CartItem>>(decodedJson)
            } catch (e: Exception) {
                emptyList<CartItem>()
            }
            ConfirmOrderScreen(
                cartItems = cartItems,
                onBackClick = { navController.popBackStack() }
            )
        }

        // My Bills screen
        composable("my_bills") {
            MyBillsScreen(navController = navController)
        }

        // Order History screen
        composable("order_history") {
            OrderHistoryScreen(navController = navController)
        }

        // My Wallet screen
        composable("my_wallet") {
            MyWalletScreen(navController = navController)
        }

        // Refund Policy screen
        composable("refund_policy") {
            RefundPolicyScreen(navController = navController)
        }

        // Grievances screen
        composable("grievances") {
            GrievancesScreen(navController = navController)
        }

        // Terms & Conditions screen
        composable("terms_conditions") {
            TermsAndConditionsScreen(navController = navController)
        }

        // About Us screen
        composable("about_us") {
            AboutUsScreen(navController = navController)
        }

        // My QR Code screen
        composable("my_qr_code") {
            MyQRCodeScreen(navController = navController)
        }
        composable("notifications") {
            NotificationScreen(navController = navController)
        }
        composable("cart") {
            CartScreen(navController = navController)
        }
    }
}