package com.favwest.medicalrpg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.favwest.medicalrpg.databinding.FragmentSelectPatientBinding

class SelectPatientFragment : Fragment() {
    private lateinit var binding: FragmentSelectPatientBinding
    private val vm: SelectPatientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_patient, container, false)
        binding.selectSamButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_selectPatientFragment_to_samFragment)
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.currentTime.text = vm.getCurrentTimeString()
    }
}