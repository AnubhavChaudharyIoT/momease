package com.example.momease.components.grids

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.momease.R
import com.example.momease.ServiceGrid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

@Composable
fun WashGrid() {
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val userId = auth.currentUser?.uid

    // Define items with metadata matching CartPopup.kt structure
    val items = listOf(
        mapOf(
            "imageRes" to R.drawable.ic_blazer,
            "name" to "Blazer",
            "price" to 79.0,
            "washSelected" to true,
            "dryCleanSelected" to false
        ),
        mapOf(
            "imageRes" to R.drawable.ic_saree,
            "name" to "Saree",
            "price" to 79.0,
            "washSelected" to true,
            "dryCleanSelected" to false
        ),
        mapOf(
            "imageRes" to R.drawable.ic_saree,
            "name" to "Silk Saree",
            "price" to 79.0,
            "washSelected" to true,
            "dryCleanSelected" to false
        ),
        mapOf(
            "imageRes" to R.drawable.ic_saree,
            "name" to "Cotton Saree",
            "price" to 79.0,
            "washSelected" to true,
            "dryCleanSelected" to false
        )
    )

    ServiceGrid(items = items.map { it["imageRes"] as Int }) { imageRes ->
        if (userId == null) {
            Toast.makeText(context, "Please login to add items to cart", Toast.LENGTH_LONG).show()
            return@ServiceGrid
        }

        // Find the selected item
        val selectedItem = items.find { it["imageRes"] == imageRes }
        if (selectedItem == null) {
            Log.e("WashGrid", "Selected item not found for imageRes: $imageRes")
            Toast.makeText(context, "Error adding item to cart", Toast.LENGTH_SHORT).show()
            return@ServiceGrid
        }

        // Prepare cart item data
        val cartItem = hashMapOf(
            "imageRes" to selectedItem["imageRes"],
            "name" to selectedItem["name"],
            "price" to selectedItem["price"],
            "quantity" to 1,
            "washSelected" to selectedItem["washSelected"],
            "dryCleanSelected" to selectedItem["dryCleanSelected"]
        )

        // Add to Firestore
        Log.d("WashGrid", "Adding item to cart: $cartItem")
        db.collection("users")
            .document(userId)
            .collection("cart")
            .document(UUID.randomUUID().toString())
            .set(cartItem)
            .addOnSuccessListener {
                Log.d("WashGrid", "Item successfully added to Firestore: ${selectedItem["name"]}")
                Toast.makeText(context, "${selectedItem["name"]} added to cart", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.e("WashGrid", "Failed to add item to Firestore: ${e.message}", e)
                Toast.makeText(context, "Failed to add item: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
}