package com.anesa.saloon.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.anesa.saloon.R

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val home: View = view.findViewById(R.id.home)
        home.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)
        }

        val addButton: View = view.findViewById(R.id.calendar)
        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_createBookingFragment)
        }

        val locations: View = view.findViewById(R.id.locations)
        locations.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_locationsFragment)
        }

        val search: View = view.findViewById(R.id.search)
        search.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_searchFragment)
        }

        val logout: Button = view.findViewById(R.id.log_out)
        logout.setOnClickListener {
            val sharedPrefs = context?.getSharedPreferences("ANESA", Context.MODE_PRIVATE)
            val editor = sharedPrefs?.edit()
            editor?.remove("USER_ID")
            editor?.apply()
            activity?.finish()
        }

        return view
    }

}