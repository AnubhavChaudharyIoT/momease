package com.example.momease.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun LaundryPatternBackground() {
    val context = LocalContext.current
    val icons = listOf(
        "file:///android_asset/icons/iron.svg",
        "file:///android_asset/icons/shirt.svg",
        "file:///android_asset/icons/socks.svg"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            repeat(6) { rowIndex ->
                Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    repeat(4) { columnIndex ->
                        val icon = icons[(rowIndex + columnIndex) % icons.size]
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(icon)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(32.dp)
                                .graphicsLayer {
                                    alpha = 0.15f // Light opacity to blend with background
                                }
                        )
                    }
                }
            }
        }
    }
}
