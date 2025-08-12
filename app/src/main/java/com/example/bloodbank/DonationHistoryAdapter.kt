package com.example.bloodbank

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bloodbank.api.DonationRecord

class DonationHistoryAdapter(private val donationList: List<DonationRecord>) :
    RecyclerView.Adapter<DonationHistoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvAge: TextView = itemView.findViewById(R.id.tvAge)
        val tvBloodGroup: TextView = itemView.findViewById(R.id.tvBloodGroup)
        val tvLastDonation: TextView = itemView.findViewById(R.id.tvLastDonation)
        val tvNextDonation: TextView = itemView.findViewById(R.id.tvNextDonation)
        val tvHospital: TextView = itemView.findViewById(R.id.tvHospital)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_donation_record, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = donationList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = donationList[position]
        holder.tvName.text = item.donor_name
        holder.tvAge.text = "${item.donor_age} years"
        holder.tvBloodGroup.text = item.blood_group
        holder.tvLastDonation.text = "Last: ${item.last_donation_date}"
        holder.tvNextDonation.text = "Next: ${item.next_eligible_date}"
        holder.tvHospital.text = item.hospital_name
        holder.tvStatus.text = item.status
    }
}
