package com.example.application.nueropro.ui.screens

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class) // ✅ Opt-in for experimental APIs
@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    var isDarkMode by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Dark Mode Toggle
            SettingsItem(
                title = "Dark Mode",
                description = if (isDarkMode) "Enabled" else "Disabled",
                icon = if (isDarkMode) Icons.Filled.DarkMode else Icons.Filled.WbSunny,
                onClick = { isDarkMode = !isDarkMode }
            )

            // Notification Toggle
            SettingsItem(
                title = "Notifications",
                description = if (notificationsEnabled) "Enabled" else "Disabled",
                icon = Icons.Filled.Notifications,
                onClick = { notificationsEnabled = !notificationsEnabled }
            )

            // Bluetooth Settings
            SettingsItem(
                title = "Bluetooth Settings",
                description = "Manage Bluetooth devices",
                icon = Icons.Filled.Bluetooth,
                onClick = { context.startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS)) }
            )

            // Privacy & Security
            SettingsItem(
                title = "Privacy & Security",
                description = "Manage your data and security",
                icon = Icons.Filled.PrivacyTip,
                onClick = { /* Navigate to Privacy Screen */ }
            )

            // Account Management
            SettingsItem(
                title = "Manage Account",
                description = "Profile, password, and settings",
                icon = Icons.Filled.Person,
                onClick = { /* Navigate to Account Screen */ }
            )

            // Logout Option
            SettingsItem(
                title = "Log Out",
                description = "Sign out of your account",
                icon = Icons.Filled.PowerSettingsNew,
                onClick = { /* Implement Logout */ }
            )
        }
    }
}

@Composable
fun SettingsItem(title: String, description: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = title, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = description, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Icon(Icons.Filled.ArrowForward, contentDescription = "Go", tint = MaterialTheme.colorScheme.primary)
        }
    }
}
