package com.example.bloodbank

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bloodbank.api.HospitalRegisterRequest
import com.example.bloodbank.api.HospitalRegisterResponse
import com.example.bloodbank.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class sign_in : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_signin)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val hospitalNameEditText = findViewById<EditText>(R.id.hospitalNameEditText)
        val cityEditText = findViewById<EditText>(R.id.cityEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val hospitalName = hospitalNameEditText.text.toString()
            val city = cityEditText.text.toString()
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val password = passwordEditText.text.toString()

            val request = HospitalRegisterRequest(
                name, hospitalName, city, email, phone, password
            )

            RetrofitClient.instance.registerHospital(request).enqueue(object : Callback<HospitalRegisterResponse> {
                override fun onResponse(
                    call: Call<HospitalRegisterResponse>,
                    response: Response<HospitalRegisterResponse>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        Toast.makeText(this@sign_in, "Registration Successful!", Toast.LENGTH_LONG).show()
                        // TODO: Redirect to login or next activity
                        finish()
                    } else {
                        Toast.makeText(this@sign_in, response.body()?.message ?: "Failed", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<HospitalRegisterResponse>, t: Throwable) {
                    Toast.makeText(this@sign_in, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}

