package com.example.bloodbank

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bloodbank.api.UserLoginRequest
import com.example.bloodbank.api.UserLoginResponse
import com.example.bloodbank.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class user_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)  // <-- your user login XML

        val emailField = findViewById<EditText>(R.id.etEmail)
        val passwordField = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val signupBtn = findViewById<Button>(R.id.signup)

        signupBtn.setOnClickListener {
            startActivity(Intent(this@user_login, user_signup::class.java))
        }

        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val request = UserLoginRequest(identifier = email, dob = password)

            RetrofitClient.instance.userLogin(request).enqueue(object : Callback<UserLoginResponse> {
                override fun onResponse(call: Call<UserLoginResponse>, response: Response<UserLoginResponse>) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        val name = response.body()?.data?.fname ?: "User"
                        Toast.makeText(this@user_login, "Welcome $name!", Toast.LENGTH_SHORT).show()
                        // Navigate to user dashboard
                        startActivity(Intent(this@user_login, user_home::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@user_login, "Login failed: ${response.body()?.message}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
                    Toast.makeText(this@user_login, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
