package com.example.momease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun TermsAndConditionsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Terms & Conditions",
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Terms & Conditions",
                color = PinkPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            val scrollState = rememberScrollState()
            Text(
                text = """
                    Welcome to Healthways Dairy! By using our services, you agree to the following terms:
                    
                    1. All products are subject to availability.
                    2. Orders once placed cannot be canceled.
                    3. Payment must be made in full at the time of order.
                    4. We reserve the right to modify these terms at any time.
                    5. For any disputes, please refer to our Grievances section.
                    
                    Please read carefully and contact us if you have any questions.
                """.trimIndent(),
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TermsAndConditionsScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val navController = androidx.navigation.compose.rememberNavController()
    TermsAndConditionsScreen(navController)
}