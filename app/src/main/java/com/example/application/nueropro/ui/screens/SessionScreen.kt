package com.example.application.nueropro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.application.nueropro.utils.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScreen(navController: NavController, appViewModel: AppViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("EEG Session") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Select an Option", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("eeg_feedback") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("EEG Feedback Using Data")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* TODO: Implement Live EEG Feature */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Live EEG Feedback")
            }
        }
    }
}
