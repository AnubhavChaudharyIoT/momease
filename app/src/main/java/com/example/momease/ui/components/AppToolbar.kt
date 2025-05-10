package com.example.momease.components  // Or your preferred package

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AppToolbar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    backgroundColor: Color = Color(0xFFE11D74),
    actions: @Composable RowScope.() -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(67.dp)
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back button (only shown if onBackClick is provided)
        onBackClick?.let { onClick ->
            IconButton(
                onClick = onClick,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }

        // Title
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .weight(1f)
                .padding(start = if (onBackClick == null) 16.dp else 8.dp)
        )

        // Actions (right side of toolbar)
        actions()
    }
}