package com.example.application.nueropro.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.application.nueropro.ui.screens.ConnectSmartwatchScreen
import com.example.application.nueropro.ui.screens.DashboardScreen
import com.example.application.nueropro.ui.screens.SplashScreen
import com.example.application.nueropro.ui.screens.WelcomeScreen
import com.example.application.nueropro.ui.screens.SessionScreen
import com.example.application.nueropro.ui.screens.SettingsScreen
import com.example.application.nueropro.ui.screens.EEGFeedbackScreen
import com.example.application.nueropro.ui.screens.AboutUsScreen
import com.example.application.nueropro.utils.AppViewModel

@Composable
fun AppNavigation(navController: NavHostController, appViewModel: AppViewModel) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen { navController.navigate("welcome") }
        }
        composable("welcome") {
            WelcomeScreen(navController, appViewModel)
        }
        composable("dashboard") {
            DashboardScreen(navController, appViewModel)
        }
        composable("session_screen") {
            SessionScreen(navController, appViewModel)
        }
        composable("settings_screen") {
            SettingsScreen(navController)
        }
        composable("eeg_feedback") {
            EEGFeedbackScreen(navController, appViewModel)
        }
        composable("about_us") {
            AboutUsScreen(navController, appViewModel)
        }
        composable("connect_smartwatch_screen") {
            ConnectSmartwatchScreen(appViewModel)
        }
    }
}
