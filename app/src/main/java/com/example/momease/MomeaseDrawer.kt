package com.example.momease

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun MomeaseDrawerContent(visible: Boolean = true, navController: NavHostController) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            initialOffsetX = { -it },
            animationSpec = tween(durationMillis = 400)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
                .padding(
                    top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding(),
                    bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
                )
                .background(Color.White, shape = RoundedCornerShape(topEnd = 26.dp, bottomEnd = 26.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 0.dp, vertical = 28.dp)
            ) {
                // User Header with Edit Button
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(100.dp)
                        .offset(y = 4.dp),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 26.dp,
                        bottomEnd = 26.dp,
                        bottomStart = 0.dp
                    ),
                    shadowElevation = 16.dp,
                    tonalElevation = 0.dp
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFFE11D74),
                                        Color(0xFFE11D74),
                                        Color(0xFFB0135B)
                                    ),
                                    radius = 900f
                                )
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_male_user),
                                contentDescription = "User",
                                modifier = Modifier
                                    .size(54.dp)
                                    .padding(6.dp)
                            )
                            Spacer(modifier = Modifier.width(14.dp))
                            Text(
                                text = "Anubhav\nChaudhary",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Image(
                            painter = painterResource(id = R.drawable.ic_edit),
                            contentDescription = "Edit Profile",
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.TopEnd)
                                .padding(top = 6.dp, end = 6.dp)
                                .clickable { navController.navigate("profile") }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // QR Code Capsule with Click Listener
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = (-90).dp, y = 4.dp)
                        .padding(end = 8.dp)
                        .wrapContentWidth(Alignment.End)
                        .clickable { navController.navigate("my_qr_code") },
                    shape = RoundedCornerShape(26.dp),
                    shadowElevation = 16.dp,
                    tonalElevation = 0.dp
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFFE11D74),
                                        Color(0xFFE11D74),
                                        Color(0xFFB0135B)
                                    ),
                                    radius = 800f
                                )
                            )
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_qrcode),
                                contentDescription = "QR Code",
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "My QR Code",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Divider(
                    color = Color.Gray.copy(alpha = 0.3f),
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                // Main Options
                Column(modifier = Modifier.padding(start = 18.dp, top = 20.dp)) {
                    DrawerOption(R.drawable.ic_wallet, "My Wallet") {
                        navController.navigate("my_wallet")
                    }
                    DrawerOption(R.drawable.ic_bills, "My Bills") {
                        navController.navigate("my_bills")
                    }
                    DrawerOption(R.drawable.ic_orders, "Orders History") {
                        navController.navigate("order_history")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Divider(
                    color = Color.Gray.copy(alpha = 0.3f),
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                // Bottom Info Options
                Column(modifier = Modifier.padding(start = 18.dp, top = 20.dp)) {
                    DrawerOption(R.drawable.ic_refund, "Refund Policy") {
                        navController.navigate("refund_policy")
                    }
                    DrawerOption(R.drawable.ic_grievances, "Grievances") {
                        navController.navigate("grievances")
                    }
                    DrawerOption(R.drawable.ic_term_conditions, "Terms & Conditions") {
                        navController.navigate("terms_conditions")
                    }
                    DrawerOption(R.drawable.ic_aboutus, "About Us") {
                        navController.navigate("about_us")
                    }
                }

                // Spacer to push version to bottom
                Spacer(modifier = Modifier.weight(1f))

                // Version Text
                Text(
                    text = "Version 1.0.0",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 12.dp),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun DrawerOption(iconRes: Int, text: String, onClick: (() -> Unit)? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp)
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
    }
}