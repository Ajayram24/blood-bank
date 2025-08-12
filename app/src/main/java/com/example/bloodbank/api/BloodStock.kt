package com.example.bloodbank.api

data class BloodStock(
    val hospital_id: Int,
    val blood_group: String,
    val units: Int,
    val notes: String?
)

data class GeneralResponse(
    val status: Boolean,
    val message: String
)
