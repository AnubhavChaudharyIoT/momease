package com.example.momease

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momease.components.grids.IronGrid
import com.example.momease.components.grids.WashGrid
import com.example.momease.ui.components.grids.DryCleanGrid

@Composable
fun ServiceSection() {
    var selectedTab by remember { mutableStateOf("Iron") }

    val tabs = listOf("Iron", "Wash", "DryClean")
    val highlightColor = Color(0xFFE11D74)

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp)
                .shadow(8.dp, RoundedCornerShape(50.dp))
                .clip(RoundedCornerShape(50.dp))
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFF0E68C), // Light yellow center
                            Color(0xFFF0E68C), // Maintain for 20% effect
                            Color(0xFFD4CA76)  // Slightly darker yellow edge
                        ),
                        radius = 750f // Adjusted for tab width
                    )
                )
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedTab = tab }
                ) {
                    Text(
                        text = tab,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    if (selectedTab == tab) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .height(4.5.dp)
                                .width(80.dp)
                                .background(highlightColor, shape = RoundedCornerShape(10.dp))
                        )
                    } else {
                        Spacer(modifier = Modifier.height(7.dp))
                    }
                }
            }
        }

        // Show grid based on selected tab
        when (selectedTab) {
            "Iron" -> IronGrid()
            "Wash" -> WashGrid()
            "DryClean" -> DryCleanGrid()
        }
    }
}