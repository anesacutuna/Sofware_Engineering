package com.anesa.saloon.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

class HomeFragment : Fragment() {

    private val apiService: ApiService = NetworkService.apiService
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        setupRecyclerView()

        // Load bookings
        loadBookings()

        val addButton: View = view.findViewById(R.id.calendar)
        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createBookingFragment)
        }

        val settings: View = view.findViewById(R.id.settings)
        settings.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }

        val search: View = view.findViewById(R.id.search)
        search.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        val hair: View = view.findViewById(R.id.hair)
        val nails: View = view.findViewById(R.id.nails)
        val makeup: View = view.findViewById(R.id.makeup)
        hair.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createBookingFragment)
        }
        nails.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createBookingFragment)
        }
        makeup.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createBookingFragment)
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
