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
fun AlternateWaveBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        val colors = listOf(
            Color(0xFFFFC1E3), Color(0xFFFFF176),
            Color(0xFFFF8A80), Color(0xFFFFD54F),
            Color(0xFFF48FB1).copy(alpha = 0.3f)
        )

        val multipliers = listOf(1f, 0.85f, 0.7f, 0.55f, 0.4f)
        val delays = listOf(0, 200, 400, 600, 800)

        for (i in colors.indices) {
            AlternateWaveLayer(
                color = colors[i],
                heightMultiplier = multipliers[i],
                offsetAmplitude = 20f - i * 3,
                delayMillis = delays[i],
                alpha = 0.85f
            )
        }
    }
}

@Composable
fun AlternateWaveLayer(
    color: Color,
    heightMultiplier: Float,
    offsetAmplitude: Float,
    delayMillis: Int,
    alpha: Float
) {
    val offsetY by rememberInfiniteTransition(label = "alt-wave-$delayMillis").animateFloat(
        initialValue = 0f,
        targetValue = offsetAmplitude,
        animationSpec = infiniteRepeatable(
            tween(4000 + delayMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offsetY"
    )

    var startOffset by remember { mutableStateOf(100f) }
    var visibleAlpha by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        delay(delayMillis.toLong())
        startOffset = 0f
        visibleAlpha = alpha
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .offset(y = (offsetY + startOffset).dp)
                .alpha(visibleAlpha)
        ) {
            val width = size.width
            val height = size.height

            val path = Path().apply {
                moveTo(0f, height * heightMultiplier)
                cubicTo(
                    width * 0.3f, height * (heightMultiplier + 0.2f),
                    width * 0.7f, height * (heightMultiplier - 0.2f),
                    width, height * heightMultiplier
                )
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(
                path = path,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        color.copy(alpha = 0.1f),
                        color.copy(alpha = 0.6f),
                        color.copy(alpha = 0.1f)
                    )
                )
            )
        }
    }
}
