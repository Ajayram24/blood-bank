package com.example.bloodbank.api

data class UserRegisterRequest(
    val fname: String,
    val dob: String,
    val gender: String,
    val phone: String,
    val mail: String,
    val blood_group: String,
    val last_donation: String?,
    val address: String,
    val city: String,
    val state: String,
    val zip: String
)
