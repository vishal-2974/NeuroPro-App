package com.example.application.brainpro

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.util.Log

class BluetoothService(context: Context) {
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    fun connectToDevice(deviceName: String) {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        pairedDevices?.find { it.name == deviceName }?.let { device ->
            Log.d("BluetoothService", "Connected to $deviceName")
        } ?: Log.e("BluetoothService", "Device not found")
    }
}
