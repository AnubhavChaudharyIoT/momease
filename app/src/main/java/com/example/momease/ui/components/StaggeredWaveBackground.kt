package com.example.momease.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun StaggeredWaveBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        val waveColors = listOf(
            Color(0xFFE11D74),                    // Primary
            Color(0xFFC41863),                    // Slightly darker
            Color(0xFFF14C98),                    // Slightly lighter
            Color(0xFFE11D74).copy(alpha = 0.2f), // Misty overlay
            Color(0xFFE11D74).copy(alpha = 0.1f)  // Ambient fog
        )

        val heightMultipliers = listOf(1.0f, 0.85f, 0.7f, 0.55f, 0.4f)
        val delays = listOf(0, 150, 300, 450, 600)

        for (i in waveColors.indices) {
            WaveLayer(
                color = waveColors[i],
                heightMultiplier = heightMultipliers[i],
                offsetAmplitude = 18f - i * 2.5f,
                alpha = 1f,
                delayMillis = delays[i]
            )
        }
    }
}


@Composable
fun WaveLayer(
    color: Color,
    heightMultiplier: Float,
    offsetAmplitude: Float,
    alpha: Float,
    delayMillis: Int
) {
    // Infinite vertical floating effect
    val infiniteTransition = rememberInfiniteTransition(label = "wave-float-$delayMillis")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = offsetAmplitude,
        animationSpec = infiniteRepeatable(
            animation = tween(3000 + delayMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offsetY"
    )

    var entryOffset by remember { mutableStateOf(100f) }
    var visibleAlpha by remember { mutableStateOf(0f) }

    // Entry animation
    LaunchedEffect(Unit) {
        delay(delayMillis.toLong())
        entryOffset = 0f
        visibleAlpha = alpha
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .offset(y = (offsetY + entryOffset).dp)
                .alpha(visibleAlpha)
        ) {
            val width = size.width
            val height = size.height

            val wave = Path().apply {
                moveTo(0f, height * heightMultiplier)
                cubicTo(
                    width * 0.25f, height * (heightMultiplier + 0.2f),
                    width * 0.75f, height * (heightMultiplier - 0.2f),
                    width, height * heightMultiplier
                )
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(
                path = wave,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        color.copy(alpha = 0.2f),
                        color.copy(alpha = 0.85f), // bright center glow
                        color.copy(alpha = 0.2f)
                    ),
                    startY = 0f,
                    endY = size.height
                )
            )


        }
    }
}
