package com.example.momease.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.momease.R
import com.example.momease.ui.theme.PinkPrimary
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

// Data model
@Serializable
data class CartItem(
    val id: String,
    val imageRes: Int,
    var quantity: Int,
    val price: Double,
    val name: String,
    val washSelected: Boolean,
    val dryCleanSelected: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavHostController) {
    var cartItems by remember { mutableStateOf<List<CartItem>>(emptyList()) }
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val userId = auth.currentUser?.uid
    val context = LocalContext.current

    // Log userId for debugging
    LaunchedEffect(Unit) {
        Log.d("CartScreen", "Current userId: $userId")
        if (userId == null) {
            Toast.makeText(context, "Please login to view cart", Toast.LENGTH_LONG).show()
        }
    }

    // Real-time listener for cart items
    LaunchedEffect(userId) {
        if (userId != null) {
            Log.d("CartScreen", "Setting up Firestore listener for user: $userId")
            db.collection("users")
                .document(userId)
                .collection("cart")
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        Log.e("CartScreen", "Firestore listener error: ${error.message}", error)
                        Toast.makeText(context, "Error fetching cart: ${error.message}", Toast.LENGTH_SHORT).show()
                        return@addSnapshotListener
                    }
                    Log.d("CartScreen", "Fetching cart items for user: $userId")
                    cartItems = snapshot?.documents?.mapNotNull { doc ->
                        val data = doc.data ?: return@mapNotNull null
                        Log.d("CartScreen", "Document data: $data")
                        try {
                            CartItem(
                                id = doc.id,
                                imageRes = (data["imageRes"] as? Long)?.toInt() ?: 0,
                                quantity = (data["quantity"] as? Long)?.toInt() ?: 1,
                                price = (data["price"] as? Double) ?: 79.0,
                                name = data["name"] as? String ?: "Product",
                                washSelected = data["washSelected"] as? Boolean ?: false,
                                dryCleanSelected = data["dryCleanSelected"] as? Boolean ?: false
                            )
                        } catch (e: Exception) {
                            Log.e("CartScreen", "Error parsing document ${doc.id}: ${e.message}", e)
                            null
                        }
                    } ?: emptyList()
                    Log.d("CartScreen", "Cart items updated: ${cartItems.size}")
                }
        } else {
            Log.w("CartScreen", "No userId, cannot fetch cart items")
            cartItems = emptyList()
        }
    }

    val totalAmount = cartItems.sumOf { it.quantity * it.price }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Cart",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PinkPrimary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (cartItems.isEmpty()) {
                // Empty State
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_empty_cart),
                        contentDescription = "Empty Cart",
                        modifier = Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Your cart is empty",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { navController.navigate("home") },
                        colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Shop Now", color = Color.White)
                    }
                }
            } else {
                // Cart Items Grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(cartItems, key = { it.id }) { item ->
                        CartItemCard(
                            item = item,
                            onIncrease = {
                                val newQuantity = item.quantity + 1
                                if (userId != null) {
                                    db.collection("users")
                                        .document(userId)
                                        .collection("cart")
                                        .document(item.id)
                                        .update("quantity", newQuantity)
                                        .addOnSuccessListener {
                                            Log.d("CartScreen", "Quantity increased: ${item.id} to $newQuantity")
                                            Toast.makeText(context, "Added one ${item.name}", Toast.LENGTH_SHORT).show()
                                            item.quantity = newQuantity
                                            cartItems = cartItems.toList()
                                        }
                                        .addOnFailureListener { e ->
                                            Log.e("CartScreen", "Failed to increase quantity: ${e.message}", e)
                                            Toast.makeText(context, "Failed to update: ${e.message}", Toast.LENGTH_LONG).show()
                                        }
                                }
                            },
                            onDecrease = {
                                if (item.quantity > 1) {
                                    val newQuantity = item.quantity - 1
                                    if (userId != null) {
                                        db.collection("users")
                                            .document(userId)
                                            .collection("cart")
                                            .document(item.id)
                                            .update("quantity", newQuantity)
                                            .addOnSuccessListener {
                                                Log.d("CartScreen", "Quantity decreased: ${item.id} to $newQuantity")
                                                Toast.makeText(context, "Removed one ${item.name}", Toast.LENGTH_SHORT).show()
                                                item.quantity = newQuantity
                                                cartItems = cartItems.toList()
                                            }
                                            .addOnFailureListener { e ->
                                                Log.e("CartScreen", "Failed to decrease quantity: ${e.message}", e)
                                                Toast.makeText(context, "Failed to update: ${e.message}", Toast.LENGTH_LONG).show()
                                            }
                                    }
                                }
                            },
                            onRemove = {
                                if (userId != null) {
                                    Log.d("CartScreen", "Attempting to delete item: ${item.id} for user: $userId")
                                    db.collection("users")
                                        .document(userId)
                                        .collection("cart")
                                        .document(item.id)
                                        .delete()
                                        .addOnSuccessListener {
                                            Log.d("CartScreen", "Item removed: ${item.id}")
                                            Toast.makeText(context, "${item.name} removed", Toast.LENGTH_SHORT).show()
                                        }
                                        .addOnFailureListener { e ->
                                            Log.e("CartScreen", "Failed to remove item: ${e.message}", e)
                                            Toast.makeText(context, "Failed to remove: ${e.message}", Toast.LENGTH_LONG).show()
                                        }
                                } else {
                                    Log.w("CartScreen", "Cannot delete: userId=$userId, item=${item.id}")
                                    Toast.makeText(context, "Cannot remove item: Not logged in", Toast.LENGTH_LONG).show()
                                }
                            }
                        )
                    }
                }

                // Checkout Section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .height(72.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Total Amount
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(
                                color = Color(0xFFFFF176),
                                shape = RoundedCornerShape(14.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Total", fontSize = 14.sp)
                            Text(
                                "₹${String.format("%.2f", totalAmount)}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Checkout Button
                    Button(
                        onClick = {
                            if (userId != null) {
                                db.collection("users")
                                    .document(userId)
                                    .collection("cart")
                                    .get()
                                    .addOnSuccessListener { snapshot ->
                                        for (doc in snapshot.documents) {
                                            doc.reference.delete()
                                        }
                                        Log.d("CartScreen", "Cart cleared")
                                        Toast.makeText(context, "Order placed!", Toast.LENGTH_SHORT).show()
                                        // Serialize cartItems to JSON and encode for URL
                                        val cartItemsJson = Json.encodeToString(cartItems)
                                        val encodedCartItems = URLEncoder.encode(cartItemsJson, StandardCharsets.UTF_8.toString())
                                        navController.navigate("confirmOrder/$encodedCartItems")
                                        cartItems = emptyList()
                                    }
                                    .addOnFailureListener { e ->
                                        Log.e("CartScreen", "Failed to clear cart: ${e.message}", e)
                                        Toast.makeText(context, "Checkout failed: ${e.message}", Toast.LENGTH_LONG).show()
                                    }
                            }
                        },
                        modifier = Modifier
                            .weight(1.5f)
                            .fillMaxHeight(),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PinkPrimary,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Checkout (${cartItems.size} items)")
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemCard(
    item: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit
) {
    // Log selections for debugging
    Log.d("CartScreen", "Rendering item: ${item.name}, washSelected: ${item.washSelected}, dryCleanSelected: ${item.dryCleanSelected}")

    Box {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Image with fixed aspect ratio
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4f / 3f)
                        .clip(RoundedCornerShape(12.dp))
                )

                // Item Name
                Text(
                    text = item.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )

                // Price
                Text(
                    text = "₹${String.format("%.2f", item.price)}",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                // Service Options
                Text(
                    text = when {
                        item.washSelected && item.dryCleanSelected -> "Wash, Dry Clean"
                        item.washSelected -> "Wash"
                        item.dryCleanSelected -> "Dry Clean"
                        else -> "Ironing"
                    },
                    color = PinkPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                // Quantity Controls
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    QuantityButton("-", onClick = onDecrease)

                    Text(
                        text = item.quantity.toString(),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    QuantityButton("+", onClick = onIncrease)
                }
            }
        }

        // Remove button
        IconButton(
            onClick = onRemove,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(12.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cancel),
                contentDescription = "Remove",
                tint = Color.Red,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun QuantityButton(
    symbol: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(32.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PinkPrimary,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = symbol,
            fontSize = 18.sp
        )
    }
}