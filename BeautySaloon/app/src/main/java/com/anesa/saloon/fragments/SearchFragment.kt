package com.anesa.saloon.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anesa.saloon.ApiService
import com.anesa.saloon.BookingAdapter
import com.anesa.saloon.NetworkService
import com.anesa.saloon.R
import com.anesa.saloon.helpers.Utils
import com.anesa.saloon.responses.Booking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    private val apiService: ApiService = NetworkService.apiService
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        setupRecyclerView()

        // Load bookings
        loadBookings()

        val addButton: View = view.findViewById(R.id.calendar)
        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_createBookingFragment)
        }

        val home: View = view.findViewById(R.id.home)
        home.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_homeFragment)
        }

        val settings: View = view.findViewById(R.id.settings)
        settings.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_settingsFragment)
        }


        return view
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
    }

    private fun loadBookings() {
        val sharedPrefs = context?.getSharedPreferences("ANESA", Context.MODE_PRIVATE)
        val userId = 1

        val call = apiService.getBookingsByUser(userId)
        call.enqueue(object : Callback<List<Booking>> {
            override fun onResponse(call: Call<List<Booking>>, response: Response<List<Booking>>) {
                if (Utils().isApiCallSuccessful(response)) {
                    val bookings = response.body()
                    if (bookings != null) {
                        adapter = BookingAdapter(bookings)
                        recyclerView.adapter = adapter
                    }
                } else {
                    Log.e("","")
                }
            }

            override fun onFailure(call: Call<List<Booking>>, t: Throwable) {
                Log.e("","")
            }
        })
    }

}