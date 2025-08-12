package com.example.bloodbank.api

data class HospitalRegisterResponse(
    val status: Boolean,
    val message: String,
    val data: HospitalRegisterData?
)

data class HospitalRegisterData(
    val hospital_id: Int
)
