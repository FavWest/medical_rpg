package com.favwest.medicalrpg

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.favwest.medicalrpg.databinding.FragmentTitleBinding
import com.favwest.medicalrpg.info.TimeCalc
import com.favwest.medicalrpg.selectpatient.SelectPatientFragment

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)
        binding.startButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setMessage("Do you want to start a new game? Previous game data will be deleted")
                .setPositiveButton("Start New Game", DialogInterface.OnClickListener {
                        _, _ ->
                    val prefs = activity?.getPreferences(Context.MODE_PRIVATE)
                    prefs?.edit()?.clear()?.apply()
                    findNavController().navigate(R.id.action_titleFragment_to_selectPatientFragment)
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                        dialog, _ -> dialog.cancel()
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Delete Previous Game Data and Restart?")
            alert.show()
        }
        binding.continueButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_selectPatientFragment))
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())||super.onOptionsItemSelected(item)
    }
}