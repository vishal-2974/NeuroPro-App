package com.example.application.nueropro.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class AppViewModel : ViewModel() {

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected

    private val _connectedDevice = MutableStateFlow("")
    val connectedDevice: StateFlow<String> = _connectedDevice

    private val _steps = MutableStateFlow(Random.nextInt(2000, 3000))
    val steps: StateFlow<Int> = _steps

    private val _calories = MutableStateFlow(Random.nextInt(100, 200))
    val calories: StateFlow<Int> = _calories

    private val _heartRate = MutableStateFlow(Random.nextInt(70, 90))
    val heartRate: StateFlow<Int> = _heartRate

    fun connectToDevice(device: String) {
        _isConnected.value = true
        _connectedDevice.value = device
    }

    fun disconnectDevice() {
        _isConnected.value = false
        _connectedDevice.value = ""
        _steps.value = Random.nextInt(2000, 3000)
        _calories.value = Random.nextInt(100, 200)
        _heartRate.value = Random.nextInt(70, 90)
    }

    fun incrementSteps() {
        _steps.value += 1
    }
}
