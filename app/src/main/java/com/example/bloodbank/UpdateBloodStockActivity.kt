package com.example.bloodbank

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager

class UpdateBloodStockActivity : AppCompatActivity() {

    private lateinit var bloodGroupRecycler: RecyclerView
    private lateinit var tvCurrentStock: TextView
    private lateinit var etUnitsToAdd: EditText
    private lateinit var etNotes: EditText
    private lateinit var tvNewTotal: TextView
    private lateinit var tvAdding: TextView
    private lateinit var btnConfirm: Button

    private var currentStock = 125  // Example value
    private var selectedGroup = "A+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_blood_stock)

        // Bind views
        bloodGroupRecycler = findViewById(R.id.bloodGroupRecycler)
        tvCurrentStock = findViewById(R.id.tvCurrentStock)
        etUnitsToAdd = findViewById(R.id.etUnitsToAdd)
        etNotes = findViewById(R.id.etNotes)
        tvNewTotal = findViewById(R.id.tvNewTotal)
        tvAdding = findViewById(R.id.tvAdding)
        btnConfirm = findViewById(R.id.btnConfirmAdd)

        // Set current stock display
        tvCurrentStock.text = "$currentStock Units"

        // Setup RecyclerView for Blood Group selection
        val bloodGroups = listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
        val adapter = BloodGroupAdapter(bloodGroups) { group ->
            selectedGroup = group
            Toast.makeText(this, "Selected: $group", Toast.LENGTH_SHORT).show()
        }
        bloodGroupRecycler.layoutManager = FlexboxLayoutManager(this)
        bloodGroupRecycler.adapter = adapter

        // Watch for unit input to calculate total
        etUnitsToAdd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val unitsToAdd = s.toString().toIntOrNull() ?: 0
                val newTotal = currentStock + unitsToAdd
                tvNewTotal.text = "New Total: $newTotal Units"
                tvAdding.text = "Adding: $unitsToAdd Units"
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Confirm Button
        btnConfirm.setOnClickListener {
            val addedUnits = etUnitsToAdd.text.toString().toIntOrNull() ?: 0
            val note = etNotes.text.toString()

            Toast.makeText(
                this,
                "Updated $addedUnits units to $selectedGroup.\nNote: $note",
                Toast.LENGTH_LONG
            ).show()

            // Here: Update to database or server logic goes

            finish() // Close activity
        }
    }
}

class FlexboxLayoutManager(updateBloodStockActivity: UpdateBloodStockActivity) : RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        TODO("Not yet implemented")
    }

}
