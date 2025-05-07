package com.example.application.nueropro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.application.nueropro.utils.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(navController: NavController, appViewModel: AppViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About Us", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EA),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to NeuroPro!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6200EA)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "NeuroPro is an innovative application designed to enhance cognitive and behavioral optimization through neurofeedback technology. Our app integrates with smartwatches, EEG headbands, and smart rings to provide real-time brainwave analysis and health insights. By leveraging cutting-edge AI and ML models, NeuroPro offers personalized feedback to help users improve focus, relaxation, and overall mental well-being.\n\nWe have implemented features such as real-time EEG stimulation, smartwatch and smart ring connectivity, and AI-driven neurofeedback analysis. The app provides insights into AQI impact on brain health, noise pollution effects, and personalized mental health suggestions. Our mission is to empower users with data-driven solutions for a healthier mind and body.\n\nStay tuned for future updates as we continue to refine NeuroPro with new features and advanced capabilities!",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        }
    }
}
