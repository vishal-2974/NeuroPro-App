package com.example.application.nueropro.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.nueropro.utils.AppViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ConnectSmartwatchScreen(viewModel: AppViewModel) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val isConnected by viewModel.isConnected.collectAsState()
    val connectedDevice by viewModel.connectedDevice.collectAsState()
    val steps by viewModel.steps.collectAsState()
    val calories by viewModel.calories.collectAsState()
    val heartRate by viewModel.heartRate.collectAsState()

    var devices by remember { mutableStateOf<List<String>>(emptyList()) }
    var isScanning by remember { mutableStateOf(false) }
    var isConnecting by remember { mutableStateOf(false) }

    val allDevices = remember {
        val randomDevices = listOf("OnePlus 9 5g ", "Samsung A55", "Xiaomi 10", "Awanish Iphone", "Samsung Galaxy")
        val fireboltPosition = Random.nextInt(1, 4)
        val shuffled = randomDevices.shuffled().take(4).toMutableList()
        shuffled.add(fireboltPosition, "ColorFit Pulse 2 Max_4510")
        shuffled
    }

    LaunchedEffect(isConnected) {
        if (isConnected) {
            while (true) {
                delay(8000)
                viewModel.incrementSteps()
            }
        }
    }

    val animatedSteps = animateFloatAsState(steps.toFloat(), tween(1000))
    val animatedCalories = animateFloatAsState(calories.toFloat(), tween(1000))
    val animatedHeartRate = animateFloatAsState(heartRate.toFloat(), tween(1000))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color(0xFFF2F4F8)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (!isConnected) "Connect to Smartwatch" else "Smartwatch Connected",
            fontSize = 24.sp,
            color = Color(0xFF37474F)
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (!isConnected) {
            Button(
                onClick = {
                    isScanning = true
                    coroutineScope.launch {
                        devices = emptyList()
                        for (i in allDevices.indices) {
                            delay(3000)
                            devices = devices + allDevices[i]
                        }
                        isScanning = false
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0D47A1))
            ) {
                Icon(Icons.Filled.Bluetooth, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Scan Devices", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isConnecting) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text("Please wait...", color = Color.Gray)
            }

            devices.forEach { device ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .clickable {
                            if (device == "ColorFit Pulse 2 Max_4510" && !isConnecting) {
                                isConnecting = true
                                coroutineScope.launch {
                                    delay(8000)
                                    viewModel.connectToDevice(device)
                                    devices = listOf(device)
                                    isConnecting = false
                                    Toast
                                        .makeText(context, "$device Connected Successfully", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        },
                    backgroundColor = Color.White,
                    elevation = 6.dp
                ) {
                    Text(device, modifier = Modifier.padding(16.dp), fontSize = 18.sp)
                }
            }
        } else {
            Text(connectedDevice, fontSize = 20.sp, color = Color(0xFF1B5E20))
            Spacer(modifier = Modifier.height(12.dp))

            StatCard("Steps", animatedSteps.value.toInt(), Color(0xFF0288D1))
            StatCard("Calories Burned", animatedCalories.value.toInt(), Color(0xFFFF5722))
            StatCard("Heart Rate", animatedHeartRate.value.toInt(), Color(0xFFE91E63))

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.disconnectDevice() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2E31C2))
            ) {
                Icon(Icons.Filled.Cancel, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Disconnect", color = Color.White)
            }
        }
    }
}

@Composable
fun StatCard(label: String, value: Int, color: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(label, fontSize = 18.sp, color = color)
        Spacer(modifier = Modifier.height(6.dp))
        LinearProgressIndicator(
            progress = (value % 10000) / 10000f,
            color = color,
            backgroundColor = color.copy(alpha = 0.3f),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text("$value", fontSize = 20.sp)
    }
}
