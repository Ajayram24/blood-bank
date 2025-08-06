// ApiService.kt
package com.example.bloodbank.api

import android.app.DownloadManager
import android.content.Context
import androidx.privacysandbox.tools.core.model.Method
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("bloodlink/hospital_login.php")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}

object ApiService {
    fun registerUser(user: RegisterUserActivity.UserModel, callback: (Boolean, String) -> Unit) {
        val url = "http://yourserver.com/bloodlink/register_user.php"
        val requestQueue = Volley.newRequestQueue(App.context)

        val jsonObject = JSONObject().apply {
            put("name", user.name)
            put("dob", user.dob)
            put("gender", user.gender)
            put("phone", user.phone)
            put("email", user.email)
            put("blood_group", user.bloodGroup)
            put("last_donation", user.lastDonation)
            put("address", user.address)
            put("city", user.city)
            put("state", user.state)
            put("zip", user.zip())
        }

        val request = JsonObjectRequest(
            DownloadManager.Request.NETWORK_WIFI.MIN_VALUE, url, jsonObject,
            { response ->
                val status = response.getBoolean("status")
                val message = response.getString("message")
                callback(status, message)
            },
            { error ->
                callback(false, "Error: ${error.message}")
            })

        requestQueue.add(request)
    }
}

class App {
    companion object {
        val context: Context? = null
    }

}
