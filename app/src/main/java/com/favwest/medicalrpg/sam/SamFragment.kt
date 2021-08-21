package com.favwest.medicalrpg.sam

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.marginTop
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.favwest.medicalrpg.R
import com.favwest.medicalrpg.databinding.FragmentSamBinding
import com.favwest.medicalrpg.info.TimeCalc

class SamFragment : Fragment() {
    private val vm : SamViewModel by viewModels()
    private lateinit var binding: FragmentSamBinding
    private lateinit var painMngButtons: List<Button>
    private lateinit var nauseaButtons: List<Button>
    private lateinit var fluidButtons: List<Button>
    private lateinit var testsButtons: List<Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sam, container, false)

        binding.apply {
            samInfo.text = getString(vm.samInfo)

            //Create onClick listeners for category buttons
            painManagement.setOnClickListener {toggleOptions(painManagementOptions)}
            nausea.setOnClickListener{ toggleOptions(nauseaOptions) }
            fluids.setOnClickListener{ toggleOptions(fluidsOptions) }
            tests.setOnClickListener{ toggleOptions(testsOptions) }

            var zofran = Button(this@SamFragment.context)
            zofran.text = getString(R.string.zofran_ondansetron_4mg_iv)
            zofran.marginTop = "16dp"
            nauseaOptions.addView(zofran)

            //Get a list of buttons for each category
            painMngButtons = listOf(morphine, dilaudid, toradol15, toradol30, lidocaine)
            nauseaButtons = listOf()//(zofran, benadryl, phenergan)
            fluidButtons = listOf(salineBolus, ringersBolus, saline100mL, ringer100mL)
            testsButtons = listOf(cbc, cmp, lipase, lacticAcid, urinalysis, doa, kidney, gallbladder, abdomen)

            //Set the click listeners for the order buttons (problem-- they mostly do the same thing right now)
            for (button in painMngButtons) { setButtonListener(binding.painManagement, "Pain Management", button, painMngButtons) }
            for (button in nauseaButtons) { setButtonListener(binding.nausea, "Nausea", button, nauseaButtons) }
            for (button in fluidButtons) { setButtonListener(binding.fluids, "Fluids", button, fluidButtons) }
            for (button in testsButtons) { setButtonListener(binding.tests, "Tests", button, testsButtons) }

            //Set click listener for Sign Orders button
            signOrders.setOnClickListener {
                val prefs = activity?.getPreferences(Context.MODE_PRIVATE)
                val currentTime = prefs?.getInt(TimeCalc.MINUTES_ELAPSED, 0)?:0
                if (prefs != null) {
                    with(prefs.edit()) {
                        putInt(TimeCalc.MINUTES_ELAPSED, currentTime + 15)
                        apply()
                    }
                }
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
        if (button.isSelected) button.setBackgroundColor(resources.getColor(R.color.blue)) else button.setBackgroundColor(resources.getColor(
            R.color.red
        ))
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