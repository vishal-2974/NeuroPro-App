package com.example.application.nueropro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.application.nueropro.ui.theme.NeuroProTheme
import com.example.application.nueropro.ui.navigation.AppNavigation
import com.example.application.nueropro.utils.AppViewModel

class MainActivity : ComponentActivity() {
    private val appViewModel: AppViewModel by viewModels() // ✅ ViewModel scoped to Activity lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeuroProTheme {
                val navController = rememberNavController()
                // Pass appViewModel to AppNavigation
                AppNavigation(navController = navController, appViewModel = appViewModel)
            }
        }
    }
}
