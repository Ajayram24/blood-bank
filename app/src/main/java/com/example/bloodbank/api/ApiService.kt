// ApiService.kt
package com.example.bloodbank.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("bloodlink/hospital_login.php")
    fun login(@Body request: LoginRequest): Call<LoginResponse>


    @Headers("Content-Type: application/json")
    @POST("hospital_register.php")
    fun registerHospital(@Body request: HospitalRegisterRequest): Call<HospitalRegisterResponse>

    @FormUrlEncoded
    @POST("add_stock.php")
    fun addStock(
        @Field("blood_group") bloodGroup: String,
        @Field("units") units: Int,
        @Field("description") description: String,
        @Field("hospital_id") hospitalId: Int
    ): Call<AddStockResponse>

    @GET("fetch_request.php")
    fun fetchRequests(
        @Query("hospital_id") hospitalId: Int
    ): Call<BloodRequestResponse>

    @GET("fetch_history.php")
    fun getHospitalHistory(
        @Query("hospital_id") hospitalId: Int
    ): Call<HistoryResponse>

    @POST("user_login.php")
    fun userLogin(@Body request: UserLoginRequest): Call<UserLoginResponse>

    @POST("user_signup.php")
    fun registerUser(@Body request: UserRegisterRequest): Call<UserRegisterResponse>



}


