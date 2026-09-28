package com.shiki.ott_ai.presentation.ViewModel.LoginCredential

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiki.ott_ai.data.remote.UserDataSource
import com.shiki.ott_ai.userdb.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userDataSource: UserDataSource
): ViewModel() {
    val user: StateFlow<List<UserEntity>> = userDataSource.getAllUser()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    private val _isSuccessful = MutableStateFlow(false)
    val isSuccessful: StateFlow<Boolean> = _isSuccessful.asStateFlow()

    var userDetail by mutableStateOf<UserEntity?>(null)

    fun onInsertUserClick(
        firstNameInput: String,
        lastNameInput: String,
        passwordInput: String,
        onSuccess: () -> Unit = {}
    ) {
        Log.d("SignUpViewModel", "onInsertUserClick called with: firstName=$firstNameInput, lastName=$lastNameInput")
        if (firstNameInput.isBlank() || lastNameInput.isBlank() || passwordInput.isBlank()) {
            Log.w("SignUpViewModel", "Insert user aborted: One or more required fields are blank.")
            return
        }
        viewModelScope.launch {
            try {
                userDataSource.insertUser(
                    firstName = firstNameInput.trim(),
                    lastName = lastNameInput.trim(),
                    password = passwordInput
                )
                Log.d("SignUpViewModel", "User successfully inserted into userEntity database table.")
                onSuccess()
            } catch (e: Exception) {
                Log.e("SignUpViewModel", "Error inserting user into database: ${e.message}", e)
            }
        }
    }

    fun getUserByName(username: String) {
        viewModelScope.launch {
            userDetail = userDataSource.getUserByName(username)
            Log.d("SignUpViewModel", "Fetched user details: $userDetail")
            if (userDetail != null) {
                _isSuccessful.value = true
            } else {
                _isSuccessful.value = false
            }
        }
    }
}
