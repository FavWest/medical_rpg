package com.favwest.medicalrpg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.favwest.medicalrpg.databinding.FragmentSelectPatientBinding

class SelectPatientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSelectPatientBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_patient, container, false)
        binding.selectSamButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_selectPatientFragment_to_samFragment)
        )
        return binding.root
    }
}