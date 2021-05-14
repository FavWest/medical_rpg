package com.favwest.medicalrpg

import android.opengl.Visibility
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import androidx.databinding.DataBindingUtil
import com.favwest.medicalrpg.databinding.ActivityMainBinding.inflate
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
            binding.painManagement.setOnClickListener {showPainManagementOptions()}
        }
        return binding.root
    }

    fun showPainManagementOptions(): Unit {
        binding.painManagementOptions.visibility = View.GONE
    }

}