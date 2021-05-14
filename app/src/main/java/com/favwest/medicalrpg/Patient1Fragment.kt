package com.favwest.medicalrpg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.favwest.medicalrpg.databinding.FragmentPatient1Binding

class Patient1Fragment : Fragment() {
    lateinit var binding: FragmentPatient1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_patient1, container, false)
        binding.apply {
            painManagement.setOnClickListener {showPainManagementOptions()}
            completePainManagement.setOnClickListener {hidePainManagementOptions()}
            val painMngButtons = listOf(morphine, dilaudid)
            for (button in painMngButtons) {
                button.setOnClickListener{changeButtonSelected(button)}
            }
        }
        return binding.root
    }

    private fun showPainManagementOptions() {
        if (binding.painManagementOptions.visibility == View.GONE)
            binding.painManagementOptions.visibility = View.VISIBLE
    }

    private fun hidePainManagementOptions() {
        if (binding.painManagementOptions.visibility == View.VISIBLE)
            binding.painManagementOptions.visibility = View.GONE
    }

    private fun changeButtonSelected(button: Button) {
        button.isSelected = !button.isSelected
        if (button.isSelected) button.setBackgroundColor(resources.getColor(R.color.blue)) else button.setBackgroundColor(resources.getColor(R.color.red))
    }
}