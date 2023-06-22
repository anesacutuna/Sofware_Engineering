package com.anesa.saloon.responses

data class BookingPayload(
    val title: String,
    val description: String,
    val serviceId: Int,
    val published: Int,
    val userId: Int,
    val status: Boolean
)