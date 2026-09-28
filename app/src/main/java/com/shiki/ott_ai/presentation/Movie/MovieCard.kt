package com.shiki.ott_ai.presentation.Movie

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.shiki.ott_ai.domain.model.Movie
import kotlin.math.abs

@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit,
    state: LazyListState,
    index: Int
) {
    val scale by remember<State<Float>> {
        derivedStateOf {
            val currentItem = state.layoutInfo.visibleItemsInfo.firstOrNull {
                it.index == index
            } ?: return@derivedStateOf 0.85f

            val viewportCenter = state.layoutInfo.viewportSize.width / 2f
            val itemCenter = currentItem.offset + (currentItem.size / 2f)
            val distanceFromCenter = abs(itemCenter - viewportCenter)


            val maxScale = 1.1f
            val minScale = 0.85f
            val scaleRange = maxScale - minScale

            val normalizedDistance = (distanceFromCenter / viewportCenter).coerceIn(0f, 1f)
            maxScale - (normalizedDistance * scaleRange)
        }
    }
    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "cardScale"
    )

    Column(
        modifier = Modifier
            .width(130.dp)
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
            }
            .clickable { onClick() }
            .padding(end = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(8.dp)),
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = movie.title,
            color = Color.White,
            fontSize = 13.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "★ ${String.format("%.1f", movie.voteAverage)}",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}
