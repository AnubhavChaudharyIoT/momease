package com.example.momease.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.momease.components.AppToolbar
import com.example.momease.ui.theme.PinkPrimary
import com.example.momease.ui.theme.YellowSecondary

@Composable
fun MyQRCodeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "My QR Code",
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "CUSTOMER",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Anubhav Chaudhary 0",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "ID SSS05971",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            // Placeholder for QR Code (replace with actual QR code generation logic)
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Replace with your QR code image or use a library
                contentDescription = "QR Code",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp)
            )
            Button(
                onClick = { /* Handle QR code scan logic here */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary)
            ) {
                Text(
                    text = "SCAN MY QR CODE",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyQRCodeScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val navController = androidx.navigation.compose.rememberNavController()
    MyQRCodeScreen(navController)
}