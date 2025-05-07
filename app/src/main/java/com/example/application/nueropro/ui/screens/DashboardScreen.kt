package com.example.application.nueropro.ui.screens

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.application.nueropro.utils.AppViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController , appViewModel: AppViewModel) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var bluetoothEnabled by remember { mutableStateOf(false) }

    // Check Bluetooth status
    val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter
    bluetoothEnabled = bluetoothAdapter?.isEnabled == true

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NeuroPro", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
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
            Spacer(modifier = Modifier.height(10.dp))

            // Sections
            DashboardSection(
                title = "Start EEG Stimulation",
                description = "Begin EEG data collection and analysis.",
                onClick = { navController.navigate("session_screen") }
            )

            DashboardSection(
                title = "Connect with Smartwatch",
                description = if (bluetoothEnabled) "Smartwatch connected successfully!" else "Connect your smartwatch via Bluetooth.",
                onClick = {navController.navigate("connect_smartwatch_screen")
                    coroutineScope.launch(Dispatchers.Main) {
                        if (!bluetoothEnabled) {
                            context.startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS))
                        }
                    }
                }
            )

            DashboardSection(
                title = "Live EEG Stimulation",
                description = "Use real-time EEG signals when the device is connected.",
                onClick = { /* Future Implementation */ }
            )

            DashboardSection(
                title = "About Us",
                description = "Learn more about the NeuroPro application and its features.",
                onClick = { navController.navigate("about_us") }
            )

            DashboardSection(
                title = "Connect with Ring",
                description = "It connects app with the Smart Ring",
                onClick = { navController.navigate("") }  // ✅ Correct navigation
            )

            DashboardSection(
                title = "Settings",
                description = "Configure your app preferences and features.",
                onClick = { navController.navigate("settings_screen") }  // ✅ Correct navigation
            )
        }
    }
}

@Composable
fun DashboardSection(title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFBB86FC))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = description, fontSize = 14.sp, color = Color.White)
            }
            Icon(Icons.Filled.ArrowForward, contentDescription = "Go", tint = Color.White)
        }
    }
}
