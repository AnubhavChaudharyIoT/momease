package com.example.momease.ui.screens

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momease.R
import com.example.momease.ui.components.StaggeredWaveBackground
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun LoginHomeScreen(onLoginClicked: () -> Unit) {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View)

    val infiniteTransition = rememberInfiniteTransition(label = "bounce")
    val bounceOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bounceOffset"
    )

    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0E68C)) // Soft yellow
    ) {
        // Gradient wave background
        StaggeredWaveBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(150.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Momease Logo",
                modifier = Modifier
                    .size(300.dp)
                    .offset(y = bounceOffset.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            Box(
                modifier = Modifier.shimmer(shimmerInstance)
            ) {
                Button(
                    onClick = {
                        isPressed = true
                        onLoginClicked()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF228B22)),
                    shape = RoundedCornerShape(35),
                    modifier = Modifier
                        .scale(if (isPressed) 0.96f else 1f)
                        .height(48.dp)
                        .width(220.dp)
                ) {
                    Text(
                        text = "Login/SignUp",
                        fontSize = 16.sp,
                        color = Color(0xFFF0E68C),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = Color(0xFFF0E68C)
                    )
                }
            }
        }
    }
}
