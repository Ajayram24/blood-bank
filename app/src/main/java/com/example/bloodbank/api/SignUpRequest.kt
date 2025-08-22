package com.example.bloodbank.api

data class SignUpRequest(
    val name: String,
    val dob: String,
    val gender: String,
    val phone: String,
    val blood: String,
    val email: String,
    val bloodGroup: String,
    val lastDonation: String,
    val address: String,
    val city: String,
    val state: String,
    val zip: String
)
