package com.shiki.ott_ai.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import com.shiki.ott_ai.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title1: String,
    font: FontFamily
) {
    TopAppBar(
        title = {
            Text(
                text = title1,
                fontFamily = font,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { /* Handle icon click */ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.image_icon_launcher),
                    contentDescription = "App Icon",
                    tint = Color.Unspecified
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Handle Profile click */ }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = Color.Cyan
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White
        )
    )
}