package com.shiki.ott_ai.presentation.Movie

import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shiki.ott_ai.domain.model.Movie

@Composable
fun MovieRow(
    title: String,
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    fontfamily: FontFamily
){
    val state = rememberLazyListState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)
    val infinteScroll = remember(movies) {
        List(50){ index -> movies[index % movies.size]}
    }
    LaunchedEffect(Unit) {
        val startMovieIndex = 0;
        val startIndex= (infinteScroll.size/2)+ startMovieIndex
        val safeIndex = startIndex.coerceIn(0, infinteScroll.lastIndex)
        state.scrollToItem(safeIndex)
    }
    if (movies.isEmpty())return
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontfamily,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow(
            state= state,
            contentPadding = PaddingValues(horizontal = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            flingBehavior = flingBehavior)
        {
            itemsIndexed(
                items =  infinteScroll,
                key = { index, movie -> "${index}_${movie}"})
            { index, movie ->
                MovieCard(
                    movie= movie,
                    onClick = {onMovieClick(movie.id)},
                    state,
                    index
                )

            }
        }
    }
}
