package com.example.waterjuggame.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun JugView(
    current: Int,
    capacity: Int,
    color: Color
) {
    // Prevent divide-by-zero crash
    val safeCapacity = if (capacity == 0) 1 else capacity

    // How full the jug is (0.0 → 1.0)
    val fillRatio = current.toFloat() / safeCapacity.toFloat()

    Box(
        modifier = Modifier
            .width(90.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(24.dp)) // This keeps water inside shape
            .border(3.dp, Color.DarkGray, RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.BottomCenter
    ) {

        // WATER LEVEL
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fillRatio)
                .background(color)
        )

        // TEXT INSIDE JUG
        Text(
            text = "${current} L",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 6.dp)
        )
    }
}
