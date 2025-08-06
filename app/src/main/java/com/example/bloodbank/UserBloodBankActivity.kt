package com.example.bloodbank

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloodbank.api.BloodBank

class UserBloodBankActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var adapter: UserBloodBankAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_blood_bank)

        rv = findViewById(R.id.rvBloodBanks)
        rv.layoutManager = LinearLayoutManager(this)

        val bloodBanks = listOf(
            BloodBank("Central Hospital Blood Bank", "2.5 km", sampleBloodMap("A+", "A-", "O+", "O-", "B-", "AB-"), "+1 (555) 123-4567", true, 4.8f),
            BloodBank("Metropolitan Medical Center", "3.8 km", sampleBloodMap("A+", "O+", "B+", "AB+"), "+1 (555) 234-5678", true, 4.6f),
            BloodBank("City General Hospital", "4.2 km", sampleBloodMap("A+", "O-", "AB+", "B+"), "+1 (555) 345-6789", true, 4.5f)
        )

        adapter = UserBloodBankAdapter(bloodBanks)
        rv.adapter = adapter
    }

    private fun sampleBloodMap(vararg available: String): Map<String, Boolean> {
        val types = listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
        return types.associateWith { it in available }
    }
}
