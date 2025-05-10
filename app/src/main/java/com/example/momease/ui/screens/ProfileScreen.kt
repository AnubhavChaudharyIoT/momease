package com.example.momease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Profile",
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
            // Profile Picture Placeholder
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(PinkPrimary, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Replace with profile icon
                    contentDescription = "Profile Picture",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Name and ID
            Text(
                text = "Anubhav Chaudhary 0",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "ID SSS05971",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Gender and DOB
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_dialog_info), // Replace with gender icon
                        contentDescription = "Gender",
                        tint = PinkPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = "Gender", color = PinkPrimary, fontSize = 14.sp)
                    Text(text = "Female", color = Color.Black, fontSize = 14.sp)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_my_calendar), // Replace with DOB icon
                        contentDescription = "DOB",
                        tint = PinkPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = "DOB", color = PinkPrimary, fontSize = 14.sp)
                    Text(text = "N/A", color = Color.Black, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mobile Numbers
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_call), // Replace with phone icon
                        contentDescription = "Mobile No.",
                        tint = PinkPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = "Mobile No.", color = PinkPrimary, fontSize = 14.sp)
                    Text(text = "8882453059", color = Color.Black, fontSize = 14.sp)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_call), // Replace with alt phone icon
                        contentDescription = "Alt Mobile No.",
                        tint = PinkPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = "Alt Mobile No.", color = PinkPrimary, fontSize = 14.sp)
                    Text(text = "N/A", color = Color.Black, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Email
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_dialog_email), // Replace with email icon
                    contentDescription = "Email",
                    tint = PinkPrimary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "anubhavchaudhary@outlook.com",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Instructions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_dialog_info), // Replace with instructions icon
                    contentDescription = "Instructions",
                    tint = PinkPrimary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "N/A",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // My Addresses
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "My Addresses",
                        color = PinkPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Permanent",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                            Text(
                                text = "NO.717, 3rd. FLOOR, sector 11 vasundhara, near mother dairy, Sector-11, Vasundhra, Ghaziabad Sector-11 Vasundhra UP",
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                        Checkbox(
                            checked = true,
                            onCheckedChange = { /* Handle set as default */ },
                            colors = CheckboxDefaults.colors(
                                checkedColor = PinkPrimary,
                                uncheckedColor = Color.Gray
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val navController = androidx.navigation.compose.rememberNavController()
    ProfileScreen(navController)
}