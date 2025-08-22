package com.example.bloodbank

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bloodbank.databinding.ActivityRoleSelectionBinding

class RoleSelectionActivity : AppCompatActivity() {

    lateinit var binding:ActivityRoleSelectionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRoleSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.adminButton.setOnClickListener {
            startActivity(Intent(this@RoleSelectionActivity,login::class.java))
        }

        binding.userButton.setOnClickListener {
            startActivity(Intent(this@RoleSelectionActivity,UserLoginActivity::class.java))
        }


    }
}