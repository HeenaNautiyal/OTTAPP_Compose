package com.shiki.ott_ai.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiki.ott_ai.domain.model.NotificationUIState
import com.shiki.ott_ai.domain.usecase.GetFcmTokenUseCase
import com.shiki.ott_ai.domain.usecase.ObserveFcmTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getFcmTokenUseCase: GetFcmTokenUseCase,
    private val observeFcmTokenUseCase: ObserveFcmTokenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationUIState())
    val uiState: StateFlow<NotificationUIState> = _uiState.asStateFlow()

    init{
        observerToken()
        fetchToken()
    }

    private fun fetchToken() {
        viewModelScope.launch {
            getFcmTokenUseCase()
                .onSuccess { token ->
                _uiState.update { it.copy(token = token.value,isLoading = false) }
            }
                .onFailure {error->
                    _uiState.update { it.copy(error = error.message, isLoading = false) }
                }

        }
    }

    private fun observerToken() {
        viewModelScope.launch {
            observeFcmTokenUseCase().collect { token ->
                _uiState.update { it.copy(token = token.value) }
            }

        }

    }

}