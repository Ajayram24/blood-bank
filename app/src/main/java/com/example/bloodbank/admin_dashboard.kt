package com.example.bloodbank

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bloodbank.databinding.ActivityAdminDashboardBinding

class admin_dashboard : AppCompatActivity() {

    lateinit var binding: ActivityAdminDashboardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addBlood.setOnClickListener {
            startActivity(Intent(this@admin_dashboard, AddBloodStockActivity::class.java))

        }

        binding.managerequest.setOnClickListener {
            startActivity(Intent(this@admin_dashboard, BloodRequestsActivity::class.java))

        }

        binding.donationhistory.setOnClickListener {
            startActivity(Intent(this@admin_dashboard, AdminDonationHistoryActivity::class.java))

        }




    }
}