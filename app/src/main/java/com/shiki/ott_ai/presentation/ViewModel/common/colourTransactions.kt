package com.shiki.ott_ai.presentation.ViewModel.common

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
    fun colourTransactions(): Brush {
    val infiniteTransition = rememberInfiniteTransition(label = "background")
    val color1 by infiniteTransition.animateColor(
        initialValue = Color(0xFF0B0D17), // Deep dark navy/black
        targetValue = Color(0xFF1A1A2E), // Dark cinematic blue
        animationSpec = infiniteRepeatable(
            animation = tween(4500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "color1"
    )
    val color2 by infiniteTransition.animateColor(
        initialValue = Color(0xFF16213E), // Deep slate blue
        targetValue = Color(0xFF0F3460), // Rich deep blue
        animationSpec = infiniteRepeatable(
            animation = tween(5500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "color2"
    )
    val color3 by infiniteTransition.animateColor(
        initialValue = Color(0xFF2D1B4E), // Very dark purple
        targetValue = Color(0xFF0A0A0A), // Near black
        animationSpec = infiniteRepeatable(
            animation = tween(6500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "color3"
    )

    val backgroundBrush = Brush.linearGradient(
        colors = listOf(color1, color2, color3),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )

    return backgroundBrush
}