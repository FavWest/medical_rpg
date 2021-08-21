package com.favwest.medicalrpg.selectpatient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.favwest.medicalrpg.R
import com.favwest.medicalrpg.databinding.FragmentSelectPatientBinding
import com.favwest.medicalrpg.info.Patients

class SelectPatientFragment : Fragment() {
    private lateinit var binding: FragmentSelectPatientBinding
    private val vm: SelectPatientViewModel by viewModels()
    private val patients = Patients()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_select_patient, container, false)
        //binding.selectSamButton.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_selectPatientFragment_to_samFragment))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.currentTime.text = getString(R.string.current_time, vm.getCurrentTimeString(this.requireActivity()))
        val patientList = patients.getPatientList(this)
        binding.patientListRecyclerView.adapter = SelectPatientAdapter(patientList)
    }
}