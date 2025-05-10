package com.example.momease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.momease.components.AppToolbar
import com.example.momease.ui.theme.PinkPrimary
import com.example.momease.ui.theme.YellowSecondary

@Composable
fun RefundPolicyScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Refund Policy",
                onBackClick = { navController.popBackStack() },
                backgroundColor = PinkPrimary
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "HEALTHWAYS DAIRY",
                color = PinkPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "REFUND POLICY",
                color = PinkPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "There is no Refund Policy available in the company.",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RefundPolicyScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val navController = androidx.navigation.compose.rememberNavController()
    RefundPolicyScreen(navController)
}