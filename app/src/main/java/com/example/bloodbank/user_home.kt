package com.example.bloodbank

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bloodbank.databinding.ActivityAdminDashboardBinding
import com.example.bloodbank.databinding.ActivityUserHomeBinding

class user_home : AppCompatActivity() {

    lateinit var binding: ActivityUserHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.history.setOnClickListener {
            startActivity(Intent(this@user_home, user_donation_history::class.java))

        }

        binding.bloodbank.setOnClickListener {
            startActivity(Intent(this@user_home,UserBloodBankActivity ::class.java))

        }

        binding.home.setOnClickListener {
            startActivity(Intent(this@user_home,user_home ::class.java))

        }

        binding.status.setOnClickListener {
            startActivity(Intent(this@user_home,user_requeststatus ::class.java))

        }

        binding.request.setOnClickListener {
            startActivity(Intent(this@user_home,user_requestblood ::class.java))

        }

        binding.profile.setOnClickListener {
            startActivity(Intent(this@user_home,user_profile ::class.java))

        }

        binding.notifications.setOnClickListener {
            startActivity(Intent(this@user_home,user_notification ::class.java))

        }
    }
}