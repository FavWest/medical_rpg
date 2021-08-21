package com.favwest.medicalrpg.sam

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.favwest.medicalrpg.R
import com.favwest.medicalrpg.info.TimeCalc

class SamViewModel: ViewModel() {
    val samInfo : Int = R.string.sam_intro

    private val timeCalc = TimeCalc()

    fun getCurrentTimeString(activity: Activity): String{
        return timeCalc.getCurrentTimeString(activity)
    }
}