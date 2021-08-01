package com.favwest.medicalrpg.selectpatient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.favwest.medicalrpg.R

class SelectPatientAdapter(private val patientList: Array<String>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.select_patient_row, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.name.text = patientList[position]
        viewHolder.context.text = patientList[position]
        viewHolder.button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_selectPatientFragment_to_samFragment))
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = patientList.size

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.name)
    val context: TextView = view.findViewById(R.id.context)
    val button: Button = view.findViewById(R.id.button)

//    init {
//        // Define click listener for the ViewHolder's View.
//    }
}