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
    lateinit var painMngButtons: List<Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_patient1, container, false)
        binding.apply {
            painManagement.setOnClickListener {showPainManagementOptions()}
            completePainManagement.setOnClickListener { hidePainManagementOptions() }
            painMngButtons = listOf(morphine, dilaudid)
            for (button in painMngButtons) {
                button.setOnClickListener{
                    changeButtonSelected(button)
                    displayPainManagementSelections()
                }
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

    private fun displayPainManagementSelections() {
        var textChange = ""
        for(button in painMngButtons) {
            if(button.isSelected) textChange += button.text.toString() + "\n"
        }
        if(textChange.length > 0) {
            textChange = textChange.slice(0.. textChange.length-2).toString()
            val message = "Pain Management:\n" + textChange
            binding.painManagement.text = message
            binding.painManagement.setBackgroundColor(resources.getColor(R.color.blue))
        } else {
            binding.painManagement.text = getString(R.string.pain_management_start_msg)
            binding.painManagement.setBackgroundColor(resources.getColor(R.color.red))
        }
    }

    private fun changeButtonSelected(button: Button) {
        button.isSelected = !button.isSelected
        if (button.isSelected) button.setBackgroundColor(resources.getColor(R.color.blue)) else button.setBackgroundColor(resources.getColor(R.color.red))
    }
}