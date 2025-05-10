package com.example.momease

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.momease.ui.navigation.AppNavGraph
import com.example.momease.ui.theme.MomeaseTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        val auth = FirebaseAuth.getInstance()
        // Sign in anonymously if no user is logged in
        if (auth.currentUser == null) {
            auth.signInAnonymously()
                .addOnSuccessListener {
                    Log.d("MainActivity", "Anonymous sign-in successful: ${auth.currentUser?.uid}")
                }
                .addOnFailureListener { e ->
                    Log.e("MainActivity", "Anonymous sign-in failed", e)
                }
        } else {
            Log.d("MainActivity", "User already signed in: ${auth.currentUser?.uid}")
        }

        setContent {
            MomeaseTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun MomeaseApp(navController: NavHostController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MomeaseDrawerContent(navController = navController)
        }
    ) {
        Scaffold(
            topBar = {
                MomeaseToolbar(
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onNotificationClick = { navController.navigate("notifications") },
                    onCartClick = { navController.navigate("cart") }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Column {
                    ImageCarousel()
                    Spacer(modifier = Modifier.height(24.dp))
                    ServiceSection()
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    MomeaseTheme {
        val previewNavController = rememberNavController()
        MomeaseApp(navController = previewNavController)
    }
}