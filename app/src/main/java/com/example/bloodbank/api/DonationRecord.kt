package com.example.bloodbank.api

data class DonationRecord(
    val name: String,
    val age: Int,
    val bloodGroup: String,
    val lastDonationDate: String,
    val nextDonationDate: String,
    val hospital: String,
    val status: String // "Eligible" or "Waiting"
)
