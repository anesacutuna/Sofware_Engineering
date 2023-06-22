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
import com.anesa.saloon.responses.LoginPayload
import com.anesa.saloon.responses.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private val apiService: ApiService = NetworkService.apiService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val loginButton = view.findViewById<Button>(R.id.loginButton)
        val guest = view.findViewById<TextView>(R.id.guestTextView)
        val registerTextView = view.findViewById<TextView>(R.id.registerTextView)

        guest.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            val user = LoginPayload(email, password)

            // Make the API call to log in the user
            loginUser(user)
        }

        registerTextView.setOnClickListener {
            // Navigate to the RegisterFragment
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return view
    }

    private fun loginUser(user: LoginPayload) {
        val call = apiService.loginUser(user)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (Utils().isApiCallSuccessful(response)) {
                    // Login successful, navigate to the HomeFragment
                    val sharedPreferences = context?.getSharedPreferences("ANESA", Context.MODE_PRIVATE)
                    val editor = sharedPreferences?.edit()
                    val intValue = response.body()?.id ?: 0
                    editor?.putInt("USER_ID", intValue)
                    editor?.apply()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
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
