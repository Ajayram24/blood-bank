package com.example.bloodbank.api

data class BloodBank(
    val name: String,
    val distance: String,
    val bloodAvailability: Map<String, Boolean>, // A+ to AB-
    val phone: String,
    val isOpen24x7: Boolean,
    val rating: Float
)

