package com.example.bloodbank

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bloodbank.api.LoginRequest
import com.example.bloodbank.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        val emailField = findViewById<EditText>(R.id.editTextEmail)
        val passwordField = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signupbtn = findViewById<TextView>(R.id.signup)


        signupbtn.setOnClickListener {
            startActivity(Intent(this@login,sign_in::class.java))
        }

        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            val request = LoginRequest(identifier = email, password = password)

            RetrofitClient.instance.login(request).enqueue(object : Callback<com.example.bloodbank.api.LoginResponse> {
                override fun onResponse(
                    call: Call<com.example.bloodbank.api.LoginResponse>,
                    response: Response<com.example.bloodbank.api.LoginResponse>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        val name = response.body()?.data?.name ?: "User"
                        Toast.makeText(this@login, "Welcome $name!", Toast.LENGTH_SHORT).show()
                        // Navigate to next screen
                        startActivity(Intent(this@login,admin_dashboard::class.java))
                    } else {
                        Toast.makeText(this@login, "Login failed: ${response.body()?.message}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<com.example.bloodbank.api.LoginResponse>, t: Throwable) {
                    Toast.makeText(this@login, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
