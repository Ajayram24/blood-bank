package com.example.bloodbank

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloodbank.api.BloodRequest
import com.example.bloodbank.api.BloodRequestResponse
import com.example.bloodbank.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BloodRequestsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BloodRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_blood_requests)

        recyclerView = findViewById(R.id.recyclerViewRequests)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = BloodRequestAdapter(
            listOf(),
            onApprove ={

            },
            onReject = {

            }
        )
        recyclerView.adapter = adapter

        fetchRequestsFromServer(1) // Example: hospital_id = 1
    }

    private fun fetchRequestsFromServer(hospitalId: Int) {
        RetrofitClient.instance.fetchRequests(hospitalId)
            .enqueue(object : Callback<BloodRequestResponse> {
                override fun onResponse(
                    call: Call<BloodRequestResponse>,
                    response: Response<BloodRequestResponse>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        val requests = response.body()?.data ?: emptyList()
                        adapter.updateData(requests)
                    } else {
                        Toast.makeText(this@BloodRequestsActivity, "No requests found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BloodRequestResponse>, t: Throwable) {
                    Toast.makeText(this@BloodRequestsActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
