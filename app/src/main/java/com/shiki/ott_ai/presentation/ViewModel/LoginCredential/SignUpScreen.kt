package com.shiki.ott_ai.presentation.ViewModel.LoginCredential

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.shiki.ott_ai.presentation.Button.ButtonDesign
import com.shiki.ott_ai.presentation.GlassTextField
import com.shiki.ott_ai.presentation.common.colourTransactions

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onNavigateToSignIn: () -> Boolean,
    onNavigateToHome: () -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colourTransactions()),
        contentAlignment = Alignment.Center
    ) {

        // Glassmorphism Card
        Box(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White.copy(alpha = 0.1f))
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(32.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Header text
                Text(
                    text = "WELCOME TO",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "Create an Account",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Input Fields
                GlassTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = "First Name",
                    icon = Icons.Default.Person
                )
                GlassTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = "Last Name",
                    icon = Icons.Default.Person
                )
                GlassTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    icon = Icons.Default.Lock,
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onPasswordToggle = { passwordVisible = !passwordVisible }
                )
                Spacer(modifier = Modifier.height(8.dp))
                ButtonDesign(
                    onClick = {
                        viewModel.onInsertUserClick(firstName, lastName, password) {
                            firstName = ""
                            lastName = ""
                            password = ""
                        onNavigateToHome()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    text = "Sign Up"
                )
            }
        }
    }
}
