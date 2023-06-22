package com.anesa.saloon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.anesa.saloon.R

class ChooseTimeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_choose_time, container, false)

        val choosetimeButton : View = view.findViewById(R.id.next)
        choosetimeButton.setOnClickListener {
            findNavController().navigate(R.id.action_chooseTimeFragment_to_createBookingFragment)
        }

        return view
    }

}