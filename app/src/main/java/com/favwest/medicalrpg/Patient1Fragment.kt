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
    private lateinit var binding: FragmentPatient1Binding
    private lateinit var painMngButtons: List<Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_patient1, container, false)
        binding.apply {
            painManagement.setOnClickListener {togglePainManagementOptions()}
            painMngButtons = listOf(morphine, dilaudid, toradol15, toradol30, lidocaine)
            for (button in painMngButtons) {
                when (button) {
                    toradol15 -> {
                        button.setOnClickListener {
                        changeButtonSelected(button)
                        incompatibleSelection(button, toradol30)
                        displayPainManagementSelections()
                        }
                    }
                    toradol30 -> {
                        button.setOnClickListener {
                            changeButtonSelected(button)
                            incompatibleSelection(button, toradol15)
                            displayPainManagementSelections()
                        }
                    }
                    else -> {
                        button.setOnClickListener {
                            changeButtonSelected(button)
                            displayPainManagementSelections()
                        }
                    }
                }
            }
        }
        return binding.root
    }

    private fun togglePainManagementOptions() {
        if (binding.painManagementOptions.visibility == View.VISIBLE)
            binding.painManagementOptions.visibility = View.GONE
        else
            binding.painManagementOptions.visibility = View.VISIBLE
    }

    private fun displayPainManagementSelections() {
        var textChange = ""
        for(button in painMngButtons) {
            if(button.isSelected) textChange += button.text.toString() + "\n"
        }
        if(textChange.isNotEmpty()) {
            textChange = textChange.slice(0.. textChange.length-2)
            val message = "Pain Management:\n$textChange"
            binding.painManagement.text = message
            @Suppress("DEPRECATION")
            binding.painManagement.setBackgroundColor(resources.getColor(R.color.blue))
        } else {
            binding.painManagement.text = getString(R.string.pain_management_start_msg)
            @Suppress("DEPRECATION")
            binding.painManagement.setBackgroundColor(resources.getColor(R.color.red))
        }
    }

    private fun changeButtonSelected(button: Button) {
        button.isSelected = !button.isSelected
        @Suppress("DEPRECATION")
        if (button.isSelected) button.setBackgroundColor(resources.getColor(R.color.blue)) else button.setBackgroundColor(resources.getColor(R.color.red))
    }

    private fun incompatibleSelection(clickedView: View, incompat: View) {
        if (clickedView.isSelected && incompat.isSelected) {
            incompat.callOnClick()
        }
    }
}