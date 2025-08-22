package com.example.bloodbank

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.bloodbank.api.RetrofitClient
import com.example.bloodbank.api.SignUpRequest
import com.example.bloodbank.api.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class user_signup: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_signup)// use your XML name

        // Get references to XML views
        val etName = findViewById<EditText>(R.id.etName)
        val etDOB = findViewById<EditText>(R.id.etDOB)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etBlood = findViewById<EditText>(R.id.etBlood)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val spinnerBloodGroup = findViewById<Spinner>(R.id.spinnerBloodGroup)
        val etLastDonation = findViewById<EditText>(R.id.etLastDonation)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val etCity = findViewById<EditText>(R.id.etCity)
        val etState = findViewById<EditText>(R.id.etState)
        val etZip = findViewById<EditText>(R.id.etZip)
        val cbTerms = findViewById<CheckBox>(R.id.cbTerms)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // Blood group options (you can customize)
        val bloodGroups = arrayOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodGroups)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBloodGroup.adapter = adapter

        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val dob = etDOB.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val blood = etBlood.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val bloodGroup = spinnerBloodGroup.selectedItem.toString()
            val lastDonation = etLastDonation.text.toString().trim()
            val address = etAddress.text.toString().trim()
            val city = etCity.text.toString().trim()
            val state = etState.text.toString().trim()
            val zip = etZip.text.toString().trim()

            // Gender selection
            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                ""
            }

            if (!cbTerms.isChecked) {
                Toast.makeText(this, "Please accept terms and conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create request object
            val request = SignUpRequest(
                name = name,
                dob = dob,
                gender = gender,
                phone = phone,
                blood = blood,
                email = email,
                bloodGroup = bloodGroup,
                lastDonation = lastDonation,
                address = address,
                city = city,
                state = state,
                zip = zip
            )

            // API call
            RetrofitClient.instance.registerUser(request).enqueue(object : Callback<SignUpResponse> {
                override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        Toast.makeText(this@user_signup, "Registration Successful!", Toast.LENGTH_LONG).show()
                        finish() // Close after success
                    } else {
                        Toast.makeText(this@user_signup, "Failed: ${response.body()?.message}", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    Toast.makeText(this@user_signup, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
