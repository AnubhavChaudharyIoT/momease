package com.example.momease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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


data class Bill(
    val orderId: String,
    val amount: String,
    val date: String,
    val paymentMode: String
)

@Composable
fun MyBillsScreen(navController: NavHostController) {
    var selectedMonth by remember { mutableStateOf("April 2025") }
    val months = listOf(
        "January 2021", "February 2021", "March 2021", "April 2021",
        "January 2022", "February 2022", "March 2022", "April 2022",
        "January 2023", "February 2023", "March 2023", "April 2023",
        "January 2024", "February 2024", "March 2024", "April 2024",
        "January 2025", "February 2025", "March 2025", "April 2025"
    )
    val bills = listOf(
        Bill("ORD000173971", "₹225.0", "12/04/2025 12:00 AM", "Wallet"),
        Bill("ORD000173399", "₹225.0", "11/04/2025 06:03 PM", "Wallet"),
        Bill("ORD000172974", "₹225.0", "10/04/2025 12:00 AM", "Wallet"),
        Bill("ORD000172395", "₹225.0", "09/04/2025 06:05 PM", "Wallet"),
        Bill("ORD000171674", "₹225.0", "08/04/2025 06:01 PM", "Wallet"),
        Bill("ORD000171177", "₹150.0", "07/04/2025 06:01 PM", "Wallet"),
        Bill("ORD000170671", "₹150.0", "06/04/2025 06:03 PM", "Wallet"),
        Bill("ORD000170180", "₹150.0", "05/04/2025 06:03 PM", "Wallet"),
        Bill("ORD000169662", "₹150.0", "04/04/2025 06:03 PM", "Wallet")
    )

    Scaffold(
        topBar = {
            AppToolbar(
                title = "My Bills",
                onBackClick = { navController.popBackStack() },
                backgroundColor = PinkPrimary
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Month Selector
            item {
                OutlinedTextField(
                    value = selectedMonth,
                    onValueChange = { selectedMonth = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    label = { Text("Select month") },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_my_calendar), // Replace with your calendar icon
                            contentDescription = "Calendar Icon",
                            tint = PinkPrimary
                        )
                    },
                    readOnly = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        unfocusedBorderColor = Color.Gray
                    )
                )
                DropdownMenu(
                    expanded = false, // This is a placeholder; you'll need a state to control it
                    onDismissRequest = { /* Handle dismiss */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    months.forEach { month ->
                        DropdownMenuItem(
                            text = { Text(month) },
                            onClick = { selectedMonth = month }
                        )
                    }
                }
            }

            // List of Bills Title
            item {
                Text(
                    text = "List of Bills",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            items(bills) { bill ->
                BillItem(bill = bill)
            }
        }
    }
}

@Composable
fun BillItem(bill: Bill) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Order ID | ${bill.orderId}",
                    color = PinkPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = bill.date,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = bill.amount,
                    color = PinkPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Mode of Payment - ${bill.paymentMode}",
                    color = PinkPrimary,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyBillsScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val navController = androidx.navigation.compose.rememberNavController()
    MyBillsScreen(navController)
}