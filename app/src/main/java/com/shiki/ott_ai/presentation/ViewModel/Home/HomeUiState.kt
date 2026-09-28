package com.shiki.ott_ai.presentation.ViewModel.Home

import com.shiki.ott_ai.domain.model.Movie

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val movies: List<Movie>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}