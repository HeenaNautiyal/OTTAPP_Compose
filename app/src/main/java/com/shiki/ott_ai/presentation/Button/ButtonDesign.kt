package com.shiki.ott_ai.presentation.Button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonDesign(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val neonColor = Color(0xFF00E5FF) // Cyan/Neon Blue

    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .
            fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color(0xFF0A0A0A).copy(alpha = 0.5f), // Semi-transparent dark center
            contentColor = neonColor
        ),
        border = BorderStroke(
            width = 2.dp,
            color = neonColor // Bright neon border
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            color = neonColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
    }
}