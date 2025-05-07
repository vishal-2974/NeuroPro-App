package com.example.application.brainpro

class DataProcessor {
    fun processData(steps: Int, eegData: String): String {
        return if (steps > 1000 && eegData.contains("EEG Signal")) {
            "Walking improves brain activity!"
        } else {
            "Increase activity for better results."
        }
    }
}
