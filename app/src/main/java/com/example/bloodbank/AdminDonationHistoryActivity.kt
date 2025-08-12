package com.example.bloodbank

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloodbank.api.DonationRecord
import com.example.bloodbank.api.HistoryResponse
import com.example.bloodbank.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminDonationHistoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DonationHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_donation_history)

        recyclerView = findViewById(R.id.recyclerDonationHistory)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = DonationHistoryAdapter(emptyList())
        recyclerView.adapter = adapter

        fetchHospitalHistory(hospitalId = 1) // example hospital ID
    }

    private fun fetchHospitalHistory(hospitalId: Int) {
        RetrofitClient.instance.getHospitalHistory(hospitalId)
            .enqueue(object : Callback<HistoryResponse> {
                override fun onResponse(
                    call: Call<HistoryResponse>,
                    response: Response<HistoryResponse>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        val historyList: List<DonationRecord> = response.body()?.data ?: emptyList()
                        adapter = DonationHistoryAdapter(historyList)
                        recyclerView.adapter = adapter
                    } else {
                        Toast.makeText(
                            this@AdminDonationHistoryActivity,
                            response.body()?.message ?: "Failed to fetch data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                    Log.e("API_ERROR", t.message.toString())
                    Toast.makeText(
                        this@AdminDonationHistoryActivity,
                        "Error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
