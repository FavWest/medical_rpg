package com.favwest.medicalrpg

import androidx.lifecycle.ViewModel

class SelectPatientViewModel: ViewModel() {
    var currentTime = 0

    fun getCurrentTimeString(): String{
        return currentTime.toString()
    }
}