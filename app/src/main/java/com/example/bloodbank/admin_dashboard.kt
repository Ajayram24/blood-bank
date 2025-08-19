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

        binding.donorlist.setOnClickListener {
            startActivity(Intent(this@admin_dashboard, DonorListActivity::class.java))

        }

        binding.btnInventory.setOnClickListener {
            startActivity(Intent(this@admin_dashboard, BloodUnitManagementActivity::class.java))

        }

        binding.btnNotifications.setOnClickListener {
            startActivity(Intent(this@admin_dashboard, admin_notifications::class.java))

        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this@admin_dashboard, Adminprofile::class.java))

        }

    }
}