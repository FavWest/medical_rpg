package com.favwest.medicalrpg.info

import androidx.fragment.app.Fragment
import com.favwest.medicalrpg.R

class Patients() {
    fun getPatientList(fragment: Fragment): Array<SelectPatientInfo>{
        return arrayOf(SelectPatientInfo(fragment.getString(R.string.sam), fragment.getString(R.string.sam_triage_text), fragment.getString(R.string.select_patient_button)),
            SelectPatientInfo(fragment.getString(R.string.vivian), fragment.getString(R.string.vivian_triage_text), fragment.getString(R.string.select_patient_button)),
            SelectPatientInfo(fragment.getString(R.string.james), fragment.getString(R.string.james_triage_text), fragment.getString(R.string.select_patient_button)),
            SelectPatientInfo(fragment.getString(R.string.lee), fragment.getString(R.string.lee_triage_text), fragment.getString(R.string.select_patient_button)),
            SelectPatientInfo(fragment.getString(R.string.place_orders), fragment.getString(R.string.place_orders_before_seeing_patients), fragment.getString(R.string.place_orders)),
        )
    }
}

data class SelectPatientInfo(val name: String, val context:String, val buttonText: String)