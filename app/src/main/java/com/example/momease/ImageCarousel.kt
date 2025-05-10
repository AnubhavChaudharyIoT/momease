package com.example.momease

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.momease.ui.theme.MomeaseTheme
import kotlin.math.absoluteValue

@Composable
fun ImageCarousel() {
    // Sample image resources (replace with your own drawables)
    val images = listOf(
        R.drawable.carousel_image_1, // Replace with your drawable resource
        R.drawable.carousel_image_2,
        R.drawable.carousel_image_3
    )

    // Pager state to manage the current page
    val pagerState = rememberPagerState(pageCount = { images.size })

    // Horizontal Pager for the carousel
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), // Adjust height as needed
        contentPadding = PaddingValues(horizontal = 60.dp), // Show 20% of left/right images
        pageSpacing = 16.dp // Space between pages
    ) { page ->
        // Calculate scale and alpha for unique sliding effect
        val offset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
        val pageOffset = offset.absoluteValue
        val scale = lerp(0.8f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
        val alpha = lerp(0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f))

        Image(
            painter = painterResource(id = images[page]),
            contentDescription = "Carousel Image ${page + 1}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)) // Rounded corners
                .background(Color.Gray) // Placeholder background if image fails to load
                .graphicsLayer {
                    // Apply scale and alpha for sliding effect
                    scaleX = scale
                    scaleY = scale
                    this.alpha = alpha
                }
                .clickable { /* Handle image click if needed */ }
        )
    }
}

// Linear interpolation helper function for smooth transitions
private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + fraction * (stop - start)
}

@Preview(showBackground = true)
@Composable
fun ImageCarouselPreview() {
    MomeaseTheme {
        ImageCarousel()
    }
}