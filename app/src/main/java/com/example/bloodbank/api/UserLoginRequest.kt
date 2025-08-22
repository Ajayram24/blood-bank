package com.example.bloodbank.api

data class UserLoginRequest(
    val identifier: String,
    val dob: String
)
