package com.example.momease.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun BubbleAndIconOverlay() {
    val bubbleColors = listOf(
        Color(0xFFAC5077).copy(alpha = 0.2f),
        Color(0xFFAC5077).copy(alpha = 0.1f)
    )
    Canvas(modifier = Modifier.fillMaxSize()) {
        repeat(30) {
            drawCircle(
                color = bubbleColors.random(),
                radius = Random.nextFloat() * 20 + 10,
                center = androidx.compose.ui.geometry.Offset(
                    Random.nextFloat() * size.width,
                    Random.nextFloat() * size.height * 0.8f
                )
            )
        }
    }
}
