package com.example.bloodbank.api

data class BloodRequest(
    val id: Int,
    val patient_name: String,
    val contact_number: String,
    val blood_type: String,
    val units: Int,
    val urgency_level: String,
    val notes: String?,
    val status: String,
    val progress: String,
    val requested_at: String
)

data class BloodRequestResponse(
    val status: Boolean,
    val message: String,
    val data: List<BloodRequest>
)
