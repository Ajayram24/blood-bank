package com.example.bloodbank

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.bloodbank.api.UserRegisterRequest
import com.example.bloodbank.api.UserRegisterResponse
import com.example.bloodbank.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class user_signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_signup) // <-- your XML file name

        val etName = findViewById<EditText>(R.id.etName)
        val etDOB = findViewById<EditText>(R.id.etDOB)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val spinnerBlood = findViewById<Spinner>(R.id.spinnerBloodGroup)
        val etLastDonation = findViewById<EditText>(R.id.etLastDonation)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val etCity = findViewById<EditText>(R.id.etCity)
        val etState = findViewById<EditText>(R.id.etState)
        val etZip = findViewById<EditText>(R.id.etZip)
        val cbTerms = findViewById<CheckBox>(R.id.cbTerms)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // Example: fill spinner with blood groups
        val bloodGroups = arrayOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
        spinnerBlood.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bloodGroups)

        btnRegister.setOnClickListener {
            val name = etName.text.toString()
            val dob = etDOB.text.toString()
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()
            val bloodGroup = spinnerBlood.selectedItem.toString()
            val lastDonation = etLastDonation.text.toString()
            val address = etAddress.text.toString()
            val city = etCity.text.toString()
            val state = etState.text.toString()
            val zip = etZip.text.toString()

            // Gender selection
            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else ""

            if (!cbTerms.isChecked) {
                Toast.makeText(this, "Please accept Terms and Conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val request = UserRegisterRequest(
                fname = name,
                dob = dob,
                gender = gender,
                phone = phone,
                mail = email,
                blood_group = bloodGroup,
                last_donation = lastDonation,
                address = address,
                city = city,
                state = state,
                zip = zip
            )

            RetrofitClient.instance.registerUser(request).enqueue(object : Callback<UserRegisterResponse> {
                override fun onResponse(call: Call<UserRegisterResponse>, response: Response<UserRegisterResponse>) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        Toast.makeText(this@user_signin, "User registered successfully!", Toast.LENGTH_LONG).show()
                        finish() // close activity
                    } else {
                        Toast.makeText(this@user_signin, response.body()?.message ?: "Failed", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<UserRegisterResponse>, t: Throwable) {
                    Toast.makeText(this@user_signin, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
