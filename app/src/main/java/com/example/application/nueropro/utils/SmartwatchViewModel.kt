// file: SmartwatchViewModel.kt
package com.example.application.nueropro.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SmartwatchViewModel : ViewModel() {
    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected

    private val _connectedDevice = MutableStateFlow<String?>(null)
    val connectedDevice: StateFlow<String?> = _connectedDevice

    fun connect(device: String) {
        _isConnected.value = true
        _connectedDevice.value = device
    }

    fun disconnect() {
        _isConnected.value = false
        _connectedDevice.value = null
    }
}
