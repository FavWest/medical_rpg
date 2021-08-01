package com.favwest.medicalrpg.selectpatient

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.favwest.medicalrpg.TimeCalc

class SelectPatientViewModel : ViewModel() {
    private val timeCalc = TimeCalc()

    fun getCurrentTimeString(activity: Activity): String{
        return timeCalc.getCurrentTimeString(activity)
    }
}

