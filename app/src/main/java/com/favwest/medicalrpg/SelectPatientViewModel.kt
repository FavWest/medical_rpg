package com.favwest.medicalrpg

import android.app.Activity
import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel

class SelectPatientViewModel : ViewModel() {

    fun getCurrentTimeString(activity: Activity): String {
        val prefs = activity.getPreferences(Context.MODE_PRIVATE)
        val currentTime = prefs.getInt(MINUTES_ELAPSED, 0)
        return buildTimeString(currentTime)
    }

    //TODO move
    @VisibleForTesting
    fun buildTimeString(time: Int): String {
        if (time/60 < 6) {
            val hours = (time/60)
            return (1800 + hours*100 + time%60).toString()
        } else {
            val extraTime = time - 360
            val hours = (extraTime/60)
            return (100 + hours*100 + extraTime%60).toString()
        }
    }

    companion object {
        const val MINUTES_ELAPSED = "Minutes Elapsed"
    }
}

