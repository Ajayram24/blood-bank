package com.example.bloodbank

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        setContentView(R.layout.activity_user_login)

        val identifierField = findViewById<EditText>(R.id.etEmail) // can be email or phone
        val dobField = findViewById<EditText>(R.id.etPassword) // date of birth
        val loginButton = findViewById<Button>(R.id.btnLogin)

        loginButton.setOnClickListener {
            val identifier = identifierField.text.toString().trim()
            val dob = dobField.text.toString().trim()

            if (identifier.isEmpty() || dob.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val request = UserLoginRequest(identifier, dob)

            RetrofitClient.instance.userLogin(request).enqueue(object : Callback<UserLoginResponse> {
                override fun onResponse(
                    call: Call<UserLoginResponse>,
                    response: Response<UserLoginResponse>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        val name = response.body()?.data?.fname ?: "User"
                        Toast.makeText(this@user_login, "Welcome $name!", Toast.LENGTH_SHORT).show()

                        // Navigate to User Dashboard
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
