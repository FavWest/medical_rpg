package com.favwest.medicalrpg.selectpatient

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.currentTime.text = getString(R.string.current_time, vm.getCurrentTimeString(this.requireActivity()))
        val patientList = patients.getPatientList(this)
        binding.patientListRecyclerView.adapter = SelectPatientAdapter(patientList)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())||super.onOptionsItemSelected(item)
    }
}