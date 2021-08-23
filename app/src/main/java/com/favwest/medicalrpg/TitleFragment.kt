package com.favwest.medicalrpg

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.favwest.medicalrpg.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)
        binding.startButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setMessage("Do you want to start a new game? Previous game data will be deleted")
                .setPositiveButton("Start New Game") { _, _ ->
                    val prefs = activity?.getPreferences(Context.MODE_PRIVATE)
                    prefs?.edit()?.clear()?.apply()
                    findNavController().navigate(R.id.action_titleFragment_to_selectPatientFragment)
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
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