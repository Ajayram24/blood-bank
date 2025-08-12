package com.example.bloodbank.api

data class HistoryResponse(
    val status: Boolean,
    val message: String,
    val data: List<DonationRecord>
)