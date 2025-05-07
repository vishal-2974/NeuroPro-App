package com.example.application.nueropro.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

object EEGDataManager {
    fun loadEEGDataFromCSV(context: Context, onResult: (String, String) -> Unit) {
        try {
            val inputStream = context.assets.open("eeg_database111.csv")
            val reader = BufferedReader(InputStreamReader(inputStream))

            var line: String?
            var alphaWaves = 0.0
            var betaWaves = 0.0

            reader.readLine() // Skip the header line

            while (reader.readLine().also { line = it } != null) {
                val data = line!!.split(",")
                if (data.size == 2) {
                    alphaWaves = data[0].toDoubleOrNull() ?: 0.0
                    betaWaves = data[1].toDoubleOrNull() ?: 0.0
                    break // Read only the first row of data
                }
            }

            reader.close()

            // ✅ EEG Analysis based on wave values
            val feedback = when {
                alphaWaves > 60.0 -> "Deep relaxation detected."
                betaWaves > 50.0 -> "Heightened focus and stress detected."
                else -> "Normal brain activity."
            }

            onResult("Alpha: $alphaWaves | Beta: $betaWaves", feedback)

        } catch (e: Exception) {
            onResult("Error", "Failed to read EEG data: ${e.localizedMessage}")
        }
    }
}
