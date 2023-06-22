package com.anesa.saloon

import com.anesa.saloon.responses.Booking
import com.anesa.saloon.responses.BookingPayload
import com.anesa.saloon.responses.LoginPayload
import com.anesa.saloon.responses.RegisterPayload
import com.anesa.saloon.responses.Service
import com.anesa.saloon.responses.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/")
    fun baseRequest(): Call<BaseResponseModel>

    @GET("/api/bookings/user/{id}")
    fun getBookingsByUser(@Path("id") userId: Int): Call<List<Booking>>

    @POST("/api/bookings/")
    fun createBooking(@Body user: BookingPayload): Call<Unit>

    @GET("/api/services")
    fun getServices(): Call<List<Service>>


    @POST("/api/user/register")
    fun registerUser(@Body user: RegisterPayload): Call<User>

    @POST("/api/user/login")
    fun loginUser(@Body user: LoginPayload): Call<User>
}
