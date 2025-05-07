package com.example.application.nueropro.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SmartwatchViewModel : ViewModel() {

    private val _heartRate = MutableStateFlow(0)
    val heartRate = _heartRate.asStateFlow()

    private val _steps = MutableStateFlow(0)
    val steps = _steps.asStateFlow()

    private val _temperature = MutableStateFlow(0.0)
    val temperature = _temperature.asStateFlow()

    fun updateHeartRate(value: Int) {
        _heartRate.value = value
    }

    fun updateSteps(value: Int) {
        _steps.value = value
    }

    fun updateTemperature(value: Double) {
        _temperature.value = value
    }
}
