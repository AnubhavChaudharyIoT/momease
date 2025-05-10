package com.example.momease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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


data class Transaction(
    val description: String,
    val amount: String,
    val date: String,
    val time: String
)

@Composable
fun MyWalletScreen(navController: NavHostController) {
    val balance = "300.00"
    val transactions = listOf(
        Transaction("Order Placed of ORD000173971", "- ₹225.00", "12/04/2025", "08:37 AM"),
        Transaction("Order Placed ORD000173399", "- ₹225.00", "10/04/2025", "06:03 PM"),
        Transaction("Order Placed of ORD000172974", "- ₹225.00", "10/04/2025", "09:46 AM"),
        Transaction("Order Placed ORD000172395", "- ₹225.00", "08/04/2025", "06:05 PM"),
        Transaction("Order Updated (Debit) of", "- ₹225.00", "08/04/2025", "08:00 PM")
    )

    Scaffold(
        topBar = {
            AppToolbar(
                title = "My Wallet",
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Balance Section
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Replace with your wallet icon
                            contentDescription = "Wallet Icon",
                            tint = PinkPrimary,
                            modifier = Modifier.size(40.dp)
                        )
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "$balance ₹",
                                color = PinkPrimary,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Balance in wallet",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            // Amount Input Section
            item {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Handle amount input */ },
                    label = { Text("₹ Enter Amount") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        unfocusedBorderColor = Color.Gray
                    )
                )
            }

            // Add Amount Button
            item {
                Button(
                    onClick = { /* Handle add amount */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_add), // Replace with your add icon
                            contentDescription = "Add Icon",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Add Amount to Wallet",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Payment History Section
            item {
                Text(
                    text = "Payment History",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            items(transactions) { transaction ->
                TransactionItem(transaction = transaction)
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = transaction.description,
                color = Color.Red,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Mode of Payment",
                    color = PinkPrimary,
                    fontSize = 14.sp
                )
                Text(
                    text = "${transaction.date} ${transaction.time}",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
            Text(
                text = "Wallet",
                color = PinkPrimary,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyWalletScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val navController = androidx.navigation.compose.rememberNavController()
    MyWalletScreen(navController)
}