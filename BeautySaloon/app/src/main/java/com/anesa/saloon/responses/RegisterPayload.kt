package com.anesa.saloon.responses

data class RegisterPayload (
    val fullName: String,
    val email: String,
    val password: String
)