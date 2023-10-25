package com.example.speedypizza.screens


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.example.speedypizza.ui.theme.center_color
import com.example.speedypizza.ui.theme.end_color
import com.example.speedypizza.ui.theme.start_color



@Composable
fun ShiftsPage(close: () -> Unit = {}) {

    val gradient = Brush.verticalGradient(
        colors = listOf(start_color, center_color, end_color ),
        startY = 0f,
        endY = 2000f
    )


}

