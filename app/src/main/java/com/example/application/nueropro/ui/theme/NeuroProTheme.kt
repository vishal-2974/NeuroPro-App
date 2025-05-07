package com.example.application.nueropro.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun NeuroProTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography,
        content = content
    )
}
