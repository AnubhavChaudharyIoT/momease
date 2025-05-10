package com.example.momease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun GrievancesScreen(navController: NavHostController) {
    var grievanceText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            AppToolbar(
                title = "Grievances",
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
                text = "Submit Your Grievance",
                color = PinkPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = grievanceText,
                onValueChange = { grievanceText = it },
                label = { Text("Describe your grievance") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PinkPrimary,
                    unfocusedBorderColor = Color.Gray
                )
            )
            Button(
                onClick = { /* Handle submission logic here */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary)
            ) {
                Text(
                    text = "Submit Grievance",
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
fun GrievancesScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val navController = androidx.navigation.compose.rememberNavController()
    GrievancesScreen(navController)
}