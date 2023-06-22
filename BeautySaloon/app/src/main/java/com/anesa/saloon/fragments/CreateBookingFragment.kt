package com.anesa.saloon.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.anesa.saloon.ApiService
import com.anesa.saloon.BookingAdapter
import com.anesa.saloon.NetworkService
import com.anesa.saloon.R
import com.anesa.saloon.ServicesAdapter
import com.anesa.saloon.responses.Booking
import com.anesa.saloon.responses.BookingPayload
import com.anesa.saloon.responses.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateBookingFragment : Fragment() {

    private val apiService: ApiService = NetworkService.apiService

    private var selectedServiceId: Int = -1 // Default value when no service is selected

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_booking, container, false)

        val titleEditText: EditText = view.findViewById(R.id.titleEditText)
        val descriptionEditText: EditText = view.findViewById(R.id.descriptionEditText)
        val submitButton: Button = view.findViewById(R.id.submitButton)
        val choosetimeButton : View = view.findViewById(R.id.choosetimeButton)
        choosetimeButton.setOnClickListener {
            findNavController().navigate(R.id.action_createBookingFragment_to_chooseTimeFragment)
        }

        val userId = getUserIdFromSharedPreferences() // Implement this method to retrieve the user ID from SharedPreferences

        // Load data from API and populate RecyclerView
//        val serviceCall = apiService.getServices()
//        serviceCall.enqueue(object : Callback<List<Service>> {
//            override fun onResponse(call: Call<List<Service>>, response: Response<List<Service>>) {
//                if (response.isSuccessful) {
//                    val services = response.body()
//                    if (services != null) {
//                        val adapter = ServicesAdapter(services) { selectedServiceId ->
//                            onServiceSelected(selectedServiceId)
//                        }
//                        recyclerView.adapter = adapter
//                    }
//                } else {
//                    // Handle API error
//                }
//            }
//
//            override fun onFailure(call: Call<List<Service>>, t: Throwable) {
//                // Handle network failure
//            }
//        })

        submitButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()

            val booking = BookingPayload(
                title = title,
                description = description,
                serviceId = 1,
                published = 0, // Set the appropriate published value
                userId = 1,
                status = false // Set the appropriate status value
            )

            val call = apiService.createBooking(booking)
            call.enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.e("","")
                    findNavController().navigate(R.id.action_createBookingFragment_to_homeFragment)
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("","")
                    Toast.makeText(context, "Failed to create new booking", Toast.LENGTH_LONG).show()
                }
            })
        }

        return view
    }

    private fun onServiceSelected(serviceId: Int) {
        selectedServiceId = serviceId
    }

    private fun getUserIdFromSharedPreferences(): Int {
        val sharedPrefs = context?.getSharedPreferences("ANESA", Context.MODE_PRIVATE)
        return sharedPrefs?.getInt("USER_ID", 1) ?: 1
    }

}