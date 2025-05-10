package com.example.momease.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun BottomWaveOverlay() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
    ) {
        drawRect(
            color = Color(0xFFC64F83),
            topLeft = Offset.Zero,
            size = Size(size.width, size.height)
        )
        repeat(50) {
            drawCircle(
                color = Color.White.copy(alpha = 0.3f),
                radius = Random.nextFloat() * 6,
                center = Offset(
                    Random.nextFloat() * size.width,
                    Random.nextFloat() * size.height
                )
            )
        }
    }
}
