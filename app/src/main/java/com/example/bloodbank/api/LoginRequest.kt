package com.example.bloodbank.api

data class LoginRequest(
    val identifier: String,
    val password: String
)

data class HospitalData(
    val id: Int,
    val name: String,
    val mail: String,
    val phone: Long
)

data class LoginResponse(
    val status: Boolean,
    val message: String,
    val data: HospitalData?
)
