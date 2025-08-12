package com.example.bloodbank.api

data class DonationRecord(
    val donor_name: String,
    val donor_age: Int,
    val blood_group: String,
    val last_donation_date: String,
    val next_eligible_date: String,
    val hospital_name: String,
    val status: String
)
