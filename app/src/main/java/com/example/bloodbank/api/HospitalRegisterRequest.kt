package com.example.bloodbank.api

data class HospitalRegisterRequest(
    val name: String,
    val hospital_name: String,
    val city: String,
    val mail: String,
    val phone: String,
    val password: String
)

