package com.favwest.medicalrpg.sam

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import com.favwest.medicalrpg.R
import com.favwest.medicalrpg.info.TimeCalc

class SamViewModel: ViewModel() {

    private val timeCalc = TimeCalc()

    fun getSamInfo(activity: Activity): Int {
        return if(!activity.getPreferences(Context.MODE_PRIVATE).getBoolean(SAM_INITIAL_VISIT, false)) R.string.sam_intro else R.string.sam_no_change
    }

    fun getCurrentTimeString(activity: Activity): String{
        return timeCalc.getCurrentTimeString(activity)
    }

    fun updatePrefs(activity: Activity) {
        val prefs = activity.getPreferences(Context.MODE_PRIVATE)
        val currentTime = prefs?.getInt(TimeCalc.MINUTES_ELAPSED, 0)?:0
        if (prefs != null) {
            with(prefs.edit()) {
                putInt(TimeCalc.MINUTES_ELAPSED, currentTime + 15)
                putBoolean(SAM_INITIAL_VISIT, true)
                apply()
            }
        }
    }
}