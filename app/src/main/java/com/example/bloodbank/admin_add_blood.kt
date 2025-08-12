package com.example.bloodbank

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bloodbank.api.RetrofitClient
import com.example.bloodbank.api.AddStockResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddBloodStockActivity : AppCompatActivity() {
    private lateinit var bloodGroupEditText: EditText
    private lateinit var currentStockTextView: TextView
    private lateinit var unitsEditText: EditText
    private lateinit var notesEditText: EditText
    private lateinit var lastUpdatedTextView: TextView
    private lateinit var addButton: Button

    private var currentStock = 124 // This should be fetched from server

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_blood_stock)

        bloodGroupEditText = findViewById(R.id.bloodGroupEditText)
        currentStockTextView = findViewById(R.id.currentStockTextView)
        unitsEditText = findViewById(R.id.unitsEditText)
        notesEditText = findViewById(R.id.notesEditText)
        lastUpdatedTextView = findViewById(R.id.lastUpdatedTextView)
        addButton = findViewById(R.id.addButton)

        currentStockTextView.text = "$currentStock Units available"

        addButton.setOnClickListener {
            val group = bloodGroupEditText.text.toString().trim()
            val unitsToAdd = unitsEditText.text.toString().toIntOrNull() ?: 0
            val notes = notesEditText.text.toString()

            if (group.isBlank() || unitsToAdd <= 0) {
                Toast.makeText(this, "Please enter valid blood group and units", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            addStockToServer(group, unitsToAdd, notes)
        }
    }

    private fun addStockToServer(bloodGroup: String, units: Int, description: String) {
        val hospitalId = 1 // Replace with actual hospital ID from session/login

        RetrofitClient.instance.addStock(bloodGroup, units, description, hospitalId)
            .enqueue(object : Callback<AddStockResponse> {
                override fun onResponse(
                    call: Call<AddStockResponse>,
                    response: Response<AddStockResponse>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        currentStock += units
                        currentStockTextView.text = "$currentStock Units available"
                        Toast.makeText(this@AddBloodStockActivity, "Stock Added", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@AddBloodStockActivity, "Failed: ${response.body()?.message}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<AddStockResponse>, t: Throwable) {
                    Toast.makeText(this@AddBloodStockActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
