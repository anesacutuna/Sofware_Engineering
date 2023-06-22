package com.anesa.saloon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.anesa.saloon.R

class LocationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_locations, container, false)

        val location1: View = view.findViewById(R.id.location1)
        val location2: View = view.findViewById(R.id.location2)
        val location3: View = view.findViewById(R.id.location3)
        val location4: View = view.findViewById(R.id.location4)


        location1.setOnClickListener {
            findNavController().navigate(R.id.action_locationsFragment_to_locationDetailsFragment)
        }
        location2.setOnClickListener {
            findNavController().navigate(R.id.action_locationsFragment_to_locationDetailsFragment)
        }
        location3.setOnClickListener {
            findNavController().navigate(R.id.action_locationsFragment_to_locationDetailsFragment)
        }
        location4.setOnClickListener {
            findNavController().navigate(R.id.action_locationsFragment_to_locationDetailsFragment)
        }

        return view
    }

}