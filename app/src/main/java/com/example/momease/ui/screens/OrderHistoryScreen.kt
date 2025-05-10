package com.example.momease.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.momease.components.AppToolbar
import com.example.momease.ui.theme.PinkPrimary
import com.example.momease.ui.theme.YellowSecondary

val PinkPrimary = Color(0xFFE11D74)
val YellowSecondary = Color(0xFFF0E68C)

data class Order(
    val orderId: String,
    val amount: String,
    val date: String,
    val status: String
)

@Composable
fun OrderHistoryScreen(navController: NavHostController) {
    val orders = listOf(
        Order("ORD000173971", "₹225.0", "12/04/2025 12:00 AM", "Delivered"),
        Order("ORD000173399", "₹225.0", "11/04/2025 06:03 PM", "Delivered"),
        Order("ORD000172974", "₹225.0", "10/04/2025 12:00 AM", "Delivered"),
        Order("ORD000172395", "₹225.0", "09/04/2025 06:05 PM", "Delivered"),
        Order("ORD000171674", "₹225.0", "08/04/2025 06:01 PM", "Delivered"),
        Order("ORD000171177", "₹150.0", "07/04/2025 06:01 PM", "Delivered"),
        Order("ORD000170671", "₹150.0", "06/04/2025 06:03 PM", "Delivered"),
        Order("ORD000170180", "₹150.0", "05/04/2025 06:03 PM", "Delivered")
    )

    Scaffold(
        topBar = {
            AppToolbar(
                title = "My Orders",
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
            item {
                TabRow(
                    selectedTabIndex = 0,
                    containerColor = YellowSecondary,
                    contentColor = PinkPrimary
                ) {
                    Tab(
                        selected = true,
                        onClick = { /* Handle tab click */ },
                        text = { Text("My Order") }
                    )
                    Tab(
                        selected = false,
                        onClick = { /* Handle tab click */ },
                        text = { Text("History") }
                    )
                }
            }
            items(orders) { order ->
                OrderItem(order = order)
            }
        }
    }
}

@Composable
fun OrderItem(order: Order) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
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
                    text = "Order ID | ${order.orderId}",
                    color = PinkPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = order.date,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = order.amount,
                    color = PinkPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = order.status,
                    color = Color.Green,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderHistoryScreenPreview() {
    OrderHistoryScreen(navController = rememberNavController()) // Remove context if not needed
}