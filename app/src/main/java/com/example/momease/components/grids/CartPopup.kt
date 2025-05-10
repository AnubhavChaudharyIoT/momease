package com.example.momease.components.dialogs

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.momease.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

@Composable
fun CartPopup(
    imageRes: Int,
    onDismiss: () -> Unit
) {
    var quantity by remember { mutableStateOf(1) }
    var washSelected by remember { mutableStateOf(false) }
    var dryCleanSelected by remember { mutableStateOf(false) }
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val userId = auth.currentUser?.uid

    // Map imageRes to item name
    val itemName = when (imageRes) {
        R.drawable.ic_tshirt2 -> "T-Shirt"
        R.drawable.ic_jeanss -> "Jeans"
        R.drawable.ic_shirt -> "Shirt"
        R.drawable.ic_pent -> "Pants"
        R.drawable.ic_sarre -> "Saree"
        R.drawable.ic_blazzer -> "Blazer"
        R.drawable.ic_kurta -> "Kurta"
        else -> "Product"
    }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .size(width = 360.dp, height = 500.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFE11D74),
                            Color(0xFF3A0D2F)
                        ),
                        radius = 1000f
                    )
                )
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                // Image
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Product",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .shadow(8.dp)
                )

                // Quantity Selector
                QuantitySelector(quantity) { newQty ->
                    quantity = newQty.coerceAtLeast(1)
                }

                // Service Options
                ServiceOptionChips(
                    washSelected = washSelected,
                    dryCleanSelected = dryCleanSelected,
                    onWashChange = { washSelected = it },
                    onDryCleanChange = { dryCleanSelected = it }
                )

                // Price & Button
                Button(
                    onClick = {
                        Log.d("CartPopup", "Add to Cart clicked, userId: $userId")
                        if (userId != null) {
                            val cartItem = hashMapOf(
                                "imageRes" to imageRes,
                                "quantity" to quantity,
                                "price" to 79.0,
                                "name" to itemName,
                                "washSelected" to washSelected,
                                "dryCleanSelected" to dryCleanSelected
                            )
                            Log.d("CartPopup", "Saving cart item: $cartItem")
                            db.collection("users")
                                .document(userId)
                                .collection("cart")
                                .document(UUID.randomUUID().toString())
                                .set(cartItem)
                                .addOnSuccessListener {
                                    Log.d("CartPopup", "Item successfully added to Firestore")
                                    onDismiss()
                                }
                                .addOnFailureListener { e ->
                                    Log.e("CartPopup", "Failed to add item to Firestore", e)
                                }
                        } else {
                            Log.w("CartPopup", "No userId, cannot add item")
                            onDismiss()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF0E68C),
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Add to Cart • ₹${79 * quantity}", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun QuantitySelector(quantity: Int, onQuantityChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.12f))
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onQuantityChange(quantity - 1) }) {
            Icon(Icons.Default.Remove, contentDescription = "Decrease", tint = Color.White)
        }
        Text(
            text = "$quantity",
            modifier = Modifier.padding(horizontal = 16.dp),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
        IconButton(onClick = { onQuantityChange(quantity + 1) }) {
            Icon(Icons.Default.Add, contentDescription = "Increase", tint = Color.White)
        }
    }
}

@Composable
fun ServiceOptionChips(
    washSelected: Boolean,
    dryCleanSelected: Boolean,
    onWashChange: (Boolean) -> Unit,
    onDryCleanChange: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        GradientChip("Wash", washSelected, onWashChange)
        GradientChip("Dry Clean", dryCleanSelected, onDryCleanChange)
    }
}

@Composable
fun GradientChip(label: String, selected: Boolean, onClick: (Boolean) -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFE11D74), Color(0xFF3A0D2F)),
                    radius = 1000f
                )
            )
            .clickable { onClick(!selected) }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = label,
            color = if (selected) Color.Black else Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}