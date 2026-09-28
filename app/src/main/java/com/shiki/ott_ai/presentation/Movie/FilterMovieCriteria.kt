package com.shiki.ott_ai.presentation.Movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shiki.ott_ai.data.remote.dto.Genre

@Composable
fun FilterMovieCriteria(
    filterList: List<Genre>,
    modifier: Modifier = Modifier
) {
    var selecteditem by remember{ mutableStateOf<Int?>(null)}
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filterList) { genre ->
            // FilterChip implementation will go here or call the component from FilterChip.kt
            FilterChip(
                selected =  selecteditem == genre.id,
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.DarkGray,
                    labelColor = Color.White,
                    selectedContainerColor = Color.Red,
                    selectedLabelColor = Color.White
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = selecteditem == genre.id,
                    selectedBorderColor = Color.Transparent
                ),
                onClick = {
                    selecteditem = genre.id
                },
                label = {
                    Text(genre.name)
                },

            )
        }
    }
}
