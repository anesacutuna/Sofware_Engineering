package com.anesa.saloon.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anesa.saloon.R
import com.anesa.saloon.helpers.Utils

class SplashFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("ANESA", Context.MODE_PRIVATE)

        // Wait for 3 seconds before navigating
        Handler().postDelayed({
            navigateToNextFragment()
        }, 3000)
    }

    private fun navigateToNextFragment() {
        val userId = sharedPreferences.getInt("USER_ID", 0)
        if (!Utils().isUserRegistered(userId))
            findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
        else
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)

    }
}
