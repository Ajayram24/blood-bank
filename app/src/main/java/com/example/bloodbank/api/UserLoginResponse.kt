package com.example.bloodbank.api

data class UserLoginResponse(
    val status: Boolean,
    val message: String,
    val data: UserData?
)

data class UserData(
    val id: String,
    val fname: String,
    val gender: String,
    val blood_group: String,
    val city: String,
    val state: String
)
