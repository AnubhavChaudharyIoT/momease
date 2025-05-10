package com.example.momease.components.grids

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.momease.R
import com.example.momease.ServiceGrid
import com.example.momease.components.dialogs.CartPopup

@Composable
fun IronGrid() {
    val items = listOf(
        R.drawable.ic_tshirt2,
        R.drawable.ic_jeanss,
        R.drawable.ic_shirt,
        R.drawable.ic_pent,
        R.drawable.ic_sarre,
        R.drawable.ic_blazzer,
        R.drawable.ic_kurta,
        R.drawable.ic_shirt
    )

    var selectedItem by remember { mutableStateOf<Int?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        ServiceGrid(items = items) { imageRes ->
            selectedItem = imageRes
        }

        selectedItem?.let { imageRes ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )
            CartPopup(imageRes = imageRes) {
                selectedItem = null
            }
        }
    }
}