package com.shiki.ott_ai.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AnimatedPermissionDialog(
    visible: Boolean,
    onDismiss: () -> Unit,
    onAllowClick: () -> Unit)
{
   AnimatedVisibility(
    visible = visible,
       enter = fadeIn(animationSpec = tween(300))+
               scaleIn(initialScale = 0.8f, animationSpec = spring(dampingRatio = 0.6f)),
       exit = fadeOut(animationSpec =  tween(200))+
               scaleOut(    targetScale = 0.8f)
   ){
       Dialog(onDismissRequest = onDismiss) {
           Box(
               modifier = Modifier
                   .fillMaxWidth()
                   .clip(RoundedCornerShape(24.dp))
                   .background(
                       brush = Brush.verticalGradient(
                           colors = listOf(
                               Color(0xFF1A1A2E),
                               Color(0xFF16213E)
                           )
                       )
                   )
                   .padding(24.dp)
           ) {
               Column(
                   horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.spacedBy(16.dp)
               ) {
                   // Icon with pulse animation
                   val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                   val scale by infiniteTransition.animateFloat(
                       initialValue = 1f,
                       targetValue = 1.15f,
                       animationSpec = infiniteRepeatable(
                           animation = tween(800, easing = FastOutSlowInEasing),
                           repeatMode = RepeatMode.Reverse
                       ),
                       label = "scale"
                   )

                   Box(
                       modifier = Modifier
                           .size(80.dp)
                           .scale(scale)
                           .clip(CircleShape)
                           .background(Color(0xFFE94560).copy(alpha = 0.2f)),
                       contentAlignment = Alignment.Center
                   ) {
                       Icon(
                           imageVector = Icons.Default.Notifications,
                           contentDescription = null,
                           tint = Color(0xFFE94560),
                           modifier = Modifier.size(40.dp)
                       )
                   }

                   Text(
                       text = "Stay Updated!",
                       color = Color.White,
                       fontSize = 22.sp,
                       fontWeight = FontWeight.Bold
                   )

                   Text(
                       text = "Allow notifications to get alerts about new movies, trending shows and exclusive content.",
                       color = Color.White.copy(alpha = 0.8f),
                       fontSize = 14.sp,
                       textAlign = TextAlign.Center,
                       lineHeight = 20.sp
                   )

                   Spacer(modifier = Modifier.height(8.dp))

                   // Allow Button
                   Button(
                       onClick = onAllowClick,
                       modifier = Modifier
                           .fillMaxWidth()
                           .height(50.dp),
                       shape = RoundedCornerShape(12.dp),
                       colors = ButtonDefaults.buttonColors(
                           containerColor = Color(0xFFE94560)
                       )
                   ) {
                       Text(
                           text = "Allow Notifications",
                           fontWeight = FontWeight.SemiBold,
                           fontSize = 16.sp
                       )
                   }

                   // Not now
                   TextButton(onClick = onDismiss) {
                       Text(
                           text = "Maybe later",
                           color = Color.White.copy(alpha = 0.6f)
                       )
                   }
               }
           }
       }
   }
}