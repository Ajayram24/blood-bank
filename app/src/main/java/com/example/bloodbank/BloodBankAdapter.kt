package com.example.bloodbank

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bloodbank.api.BloodBank

class UserBloodBankAdapter(private val list: List<BloodBank>) :
    RecyclerView.Adapter<UserBloodBankAdapter.BloodBankViewHolder>() {

    inner class BloodBankViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvName)
        val distance: TextView = view.findViewById(R.id.tvDistance)
        val rating: TextView = view.findViewById(R.id.tvRating)
        val phone: TextView = view.findViewById(R.id.tvPhone)
        val open: TextView = view.findViewById(R.id.tvOpen)
        val grid: GridLayout = view.findViewById(R.id.gridBloodTypes)
        val btnDetails: Button = view.findViewById(R.id.btnDetails)
        val btnLocation: ImageButton = view.findViewById(R.id.btnLocation)
        val btnShare: ImageButton = view.findViewById(R.id.btnShare)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodBankViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_blood_bank, parent, false)
        return BloodBankViewHolder(view)
    }

    override fun onBindViewHolder(holder: BloodBankViewHolder, position: Int) {
        val item = list[position]
        holder.name.text = item.name
        holder.distance.text = item.distance
        holder.rating.text = "★ ${item.rating}"
        holder.phone.text = "📞 ${item.phone}"
        holder.open.text = if (item.isOpen24x7) "🕒 Open 24/7" else "Closed"

        holder.grid.removeAllViews()
        val context = holder.itemView.context

        item.bloodAvailability.forEach { (type, available) ->
            val tv = TextView(context).apply {
                text = type
                setTextColor(if (available) Color.GREEN else Color.GRAY)
                setPadding(8, 4, 8, 4)
                setBackgroundResource(android.R.drawable.btn_default_small)
            }
            holder.grid.addView(tv)
        }

        holder.btnDetails.setOnClickListener {
            Toast.makeText(context, "Details for ${item.name}", Toast.LENGTH_SHORT).show()
        }
        // Add similar listeners for share/location
    }

    override fun getItemCount(): Int = list.size
}

