package com.example.application.nueropro

import android.content.Context
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

object EEGProcessor {

    fun loadEEGData(context: Context, filename: String): List<Double> {
        val eegData = mutableListOf<Double>()
        try {
            val inputStream = context.assets.open(filename)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val jsonString = reader.readText()
            reader.close()

            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                eegData.add(jsonArray.getDouble(i))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return eegData
    }

    fun analyzeEEG(eegData: List<Double>): String {
        if (eegData.isEmpty()) return "No EEG Data Found"

        val avgSignal = eegData.average()

        return when {
            avgSignal < 0.2 -> "Low Brain Activity - You seem relaxed."
            avgSignal in 0.2..0.5 -> "Normal Brain Activity - You are focused."
            avgSignal > 0.5 -> "High Brain Activity - You might be stressed."
            else -> "Unknown Brain State"
        }
    }
}
