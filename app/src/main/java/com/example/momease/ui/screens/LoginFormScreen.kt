package com.example.momease.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momease.R
import com.example.momease.ui.components.LaundryPatternBackground
import com.example.momease.ui.components.StaggeredWaveBackground
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun LoginFormScreen(
    onVerifyOtpClick: () -> Unit,
    onGoogleSignIn: () -> Unit,
    onFacebookSignIn: () -> Unit,
    onBackClick: () -> Unit
) {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View)
    var phoneNumber by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0E68C)) // Soft Yellow Background
    ) {
        LaundryPatternBackground()
        // Wave Background
        StaggeredWaveBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Back Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .clickable { onBackClick() },
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Mobile Input
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Mobile Number") },
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFE11D74),
                    focusedLabelColor = Color(0xFFE11D74),
                    cursorColor = Color(0xFFE11D74)
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Verify Button with shimmer
            Box(modifier = Modifier.shimmer(shimmer)) {
                Button(
                    onClick = { onVerifyOtpClick() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D74)),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(0.6f)
                ) {
                    Text("Verify OTP", fontSize = 16.sp, color = Color(0xFFF0E68C))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = Color(0xFFF0E68C),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text("Or sign in using", color = Color(0xFFE11D74))

            Spacer(modifier = Modifier.height(20.dp))

            // Google Sign-In
            Button(
                onClick = { onGoogleSignIn() },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google Sign-In",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Continue with Google", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Facebook Sign-In
            Button(
                onClick = { onFacebookSignIn() },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1877F2)),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "Facebook Sign-In",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Continue with Facebook", color = Color.White)
            }
        }
    }
}