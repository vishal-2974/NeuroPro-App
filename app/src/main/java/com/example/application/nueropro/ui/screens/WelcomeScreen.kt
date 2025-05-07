package com.example.application.nueropro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.application.nueropro.utils.AppViewModel

@Composable
fun WelcomeScreen(navController: NavController, appViewModel: AppViewModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Welcome to NeuroPro", style = MaterialTheme.typography.headlineLarge)
        Button(onClick = { navController.navigate("dashboard") }) {
            Text("Get Started")
        }
    }
}
