package com.example.application.nueropro.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.IOException
import androidx.compose.foundation.shape.RoundedCornerShape // <-- Add this import
import com.example.application.nueropro.utils.AppViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EEGFeedbackScreen(navController: NavController, appViewModel: AppViewModel) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var brainDataDisplay by remember { mutableStateOf("Enter User ID and Analyze") }
    var brainHealthFeedback by remember { mutableStateOf("No Data Available") }
    var aqiImpactDisplay by remember { mutableStateOf("No Data Available") }
    var overallHealthFeedback by remember { mutableStateOf("No Data Available") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("EEG Feedback", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)) // Light Gray Background
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()), // Make screen scrollable
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Enter User ID") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    loadEEGDataFromCSV(context, searchQuery) { brainData, brainFeedback, aqiImpact, healthFeedback ->
                        brainDataDisplay = brainData
                        brainHealthFeedback = brainFeedback
                        aqiImpactDisplay = aqiImpact
                        overallHealthFeedback = healthFeedback
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Analyze", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // EEG Data Card
            InfoCard(title = "Brainwave Data", content = brainDataDisplay, color = Color.Blue)

            // Brainwave Health Feedback
            InfoCard(title = "Brainwave Health Feedback", content = brainHealthFeedback, color = Color.Green)

            // AQI Impact
            InfoCard(title = "AQI Impact", content = aqiImpactDisplay, color = Color.Magenta)

            // Overall Health Feedback
            InfoCard(title = "Overall Health Feedback", content = overallHealthFeedback, color = Color.Red)

            Spacer(modifier = Modifier.height(16.dp))

            // Information Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "How AQI & Brain Waves Affect Health",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1565C0)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Poor AQI increases stress and reduces cognitive ability. High Beta waves cause anxiety, while high Delta waves indicate unconsciousness. Alpha waves promote relaxation. Proper brainwave balance is crucial for mental clarity, focus, and emotional stability.",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}

@Composable
fun InfoCard(title: String, content: String, color: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.2f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = color)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = content, fontSize = 16.sp)
        }
    }
}

// Function to load EEG data from CSV
private fun loadEEGDataFromCSV(context: Context, userId: String, onResult: (String, String, String, String) -> Unit) {
    try {
        val inputStream = context.assets.open("eeg_aqi_data_1000.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?
        var found = false

        reader.readLine() // Skip header

        while (reader.readLine().also { line = it } != null) {
            val data = line!!.split(",")
            if (data.size >= 7 && data[0] == userId) {
                val alpha = data[1].toDoubleOrNull() ?: 0.0
                val beta = data[2].toDoubleOrNull() ?: 0.0
                val gamma = data[3].toDoubleOrNull() ?: 0.0
                val theta = data[4].toDoubleOrNull() ?: 0.0
                val delta = data[5].toDoubleOrNull() ?: 0.0
                val aqi = data[6].toIntOrNull() ?: -1

                val brainData = """
                    - Alpha: $alpha
                    - Beta: $beta
                    - Gamma: $gamma
                    - Theta: $theta
                    - Delta: $delta
                    - AQI: $aqi
                """.trimIndent()

                val brainFeedback = analyzeBrainWaves(alpha, beta, gamma, theta, delta)
                val (aqiImpact, healthFeedback) = analyzeAQIImpact(aqi, beta, gamma, theta, delta)

                onResult(brainData, brainFeedback, aqiImpact, healthFeedback)
                found = true
                break
            }
        }

        if (!found) {
            onResult("No Data Found", "Invalid User ID", "No AQI Data", "No Health Feedback")
        }

    } catch (e: IOException) {
        onResult("Error Loading Data", "Could not process EEG signals.", "No AQI Data", "No Health Feedback")
    }
}

// Function to analyze Brain Waves
private fun analyzeBrainWaves(alpha: Double, beta: Double, gamma: Double, theta: Double, delta: Double): String {
    return when {
        delta > 80 -> "⚠️ High Delta: Possible deep unconsciousness risk."
        theta > 70 -> "⚠️ High Theta: Extreme drowsiness."
        beta > 60 -> "⚠️ High Beta: Stress & anxiety."
        gamma > 50 -> "⚠️ Elevated Gamma: Mental fatigue."
        alpha > 60 -> "✅ High Alpha: Relaxation state."
        else -> "✅ Brain activity is normal."
    }
}

// Function to analyze AQI Impact
private fun analyzeAQIImpact(aqi: Int, beta: Double, gamma: Double, theta: Double, delta: Double): Pair<String, String> {
    return if (aqi > 100) {
        Pair("⚠️ Poor AQI impacts cognitive function.", "⚠️ High pollution may increase stress and brain fog.")
    } else {
        Pair("✅ Good AQI conditions.", "✅ Minimal impact on brain function.")
    }
}
