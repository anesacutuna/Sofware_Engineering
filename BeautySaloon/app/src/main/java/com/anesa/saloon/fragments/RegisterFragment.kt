package com.anesa.saloon.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.anesa.saloon.ApiService
import com.anesa.saloon.NetworkService
import com.anesa.saloon.R
import com.anesa.saloon.helpers.Utils
import com.anesa.saloon.responses.RegisterPayload
import com.anesa.saloon.responses.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    private val apiService: ApiService = NetworkService.apiService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val fullNameEditText = view.findViewById<EditText>(R.id.fullNameEditText)
        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val registerButton = view.findViewById<Button>(R.id.registerButton)
        val loginTextView = view.findViewById<TextView>(R.id.loginTextView)

        registerButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            val user = RegisterPayload(fullName, email, password)

            // Make the API call to register the user
            registerUser(user)
        }

        loginTextView.setOnClickListener {
            // Navigate to the LoginFragment
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return view
    }

    private fun registerUser(user: RegisterPayload) {
        val call = apiService.registerUser(user)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (Utils().isApiCallSuccessful(response)) {
                    // Registration successful, navigate to the HomeFragment
                    val sharedPreferences = context?.getSharedPreferences("ANESA", Context.MODE_PRIVATE)
                    val editor = sharedPreferences?.edit()
                    val intValue = response.body()?.id ?: 0
                    editor?.putInt("USER_ID", intValue)
                    editor?.apply()
                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                } else {
                    // Handle API error
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Handle network failure
            }
        })
    }
}
