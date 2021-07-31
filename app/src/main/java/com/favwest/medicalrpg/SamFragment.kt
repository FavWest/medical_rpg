package com.favwest.medicalrpg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.favwest.medicalrpg.databinding.FragmentSamBinding

class SamFragment : Fragment() {
    private lateinit var binding: FragmentSamBinding
    private lateinit var painMngButtons: List<Button>
    private lateinit var nauseaButtons: List<Button>
    private lateinit var fluidButtons: List<Button>
    private lateinit var testsButtons: List<Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sam, container, false)
        binding.apply {
            painManagement.setOnClickListener {toggleOptions(painManagementOptions)}
            nausea.setOnClickListener{ toggleOptions(nauseaOptions) }
            fluids.setOnClickListener{ toggleOptions(fluidsOptions) }
            tests.setOnClickListener{ toggleOptions(testsOptions) }
            painMngButtons = listOf(morphine, dilaudid, toradol15, toradol30, lidocaine)
            nauseaButtons = listOf(zofran, benadryl, phenergan)
            fluidButtons = listOf(salineBolus, ringersBolus, saline100mL, ringer100mL)
            testsButtons = listOf(cbc, cmp, lipase, lacticAcid, urinalysis, doa, kidney, gallbladder, abdomen)
            for (button in painMngButtons) { setButtonListener(binding.painManagement, "Pain Management", button, painMngButtons) }
            for (button in nauseaButtons) { setButtonListener(binding.nausea, "Nausea", button, nauseaButtons) }
            for (button in fluidButtons) { setButtonListener(binding.fluids, "Fluids", button, fluidButtons) }
            for (button in testsButtons) { setButtonListener(binding.tests, "Tests", button, testsButtons) }
            signOrders.setOnClickListener {
                it.findNavController().navigate(R.id.action_samFragment_to_selectPatientFragment)
            }
        }
        return binding.root
    }

    
    private fun setButtonListener(medicalConcernButton: Button, medicalConcern: String, button: Button, buttonList:List<Button>) {
        when (button) {
            binding.toradol15 -> {
                button.setOnClickListener {
                    changeButtonSelected(button)
                    incompatibleSelection(button, binding.toradol30)
                    displaySelections(buttonList, medicalConcern, medicalConcernButton)
                }
            }
            binding.toradol30 -> {
                button.setOnClickListener {
                    changeButtonSelected(button)
                    incompatibleSelection(button, binding.toradol15)
                    displaySelections(buttonList, medicalConcern, medicalConcernButton)
                }
            }
            else -> {
                button.setOnClickListener {
                    changeButtonSelected(button)
                    displaySelections(buttonList, medicalConcern, medicalConcernButton)
                }
            }
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
    private fun toggleOptions(linearLayout: LinearLayout) {
        if (linearLayout.visibility == View.VISIBLE)
            linearLayout.visibility = View.GONE
        else
            linearLayout.visibility = View.VISIBLE
    }

    private fun displaySelections(buttonList: List<Button>, medicalConcern: String, medicalConcernButton: Button) {
        var textChange = ""
        for(button in buttonList) {
            if(button.isSelected) textChange += button.text.toString() + "\n"
        }
        if(textChange.isNotEmpty()) {
            textChange = textChange.slice(0.. textChange.length-2)
            val message = "$medicalConcern:\n$textChange"
            medicalConcernButton.text = message
            @Suppress("DEPRECATION")
            medicalConcernButton.setBackgroundColor(resources.getColor(R.color.blue))
        } else {
            val message = "$medicalConcern:\nClick to Select"
            medicalConcernButton.text = message
            @Suppress("DEPRECATION")
            medicalConcernButton.setBackgroundColor(resources.getColor(R.color.red))
        }
    }


}

//TODO: can I make patient fragments generic?