package com.example.bloodbank

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bloodbank.api.BloodRequest

class BloodRequestAdapter(
    private var requestList: List<BloodRequest>,
    private val onApprove: (BloodRequest) -> Unit,
    private val onReject: (BloodRequest) -> Unit
) : RecyclerView.Adapter<BloodRequestAdapter.RequestViewHolder>() {

    class RequestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textRequestCode: TextView = view.findViewById(R.id.textRequestCode)
        val textStatus: TextView = view.findViewById(R.id.textStatus)
        val textPatientName: TextView = view.findViewById(R.id.textPatientName)
        val textDepartment: TextView = view.findViewById(R.id.textDepartment)
        val buttonApprove: Button = view.findViewById(R.id.buttonApprove)
        val buttonReject: Button = view.findViewById(R.id.buttonReject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.blood_request_list, parent, false)
        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val request = requestList[position]

        holder.textRequestCode.text = "• BL-${request.id.toString().padStart(4, '0')}"
        holder.textStatus.text = request.status
        holder.textPatientName.text = request.patient_name
        holder.textDepartment.text = request.notes ?: "N/A"

        holder.buttonApprove.setOnClickListener { onApprove(request) }
        holder.buttonReject.setOnClickListener { onReject(request) }
    }

    override fun getItemCount(): Int = requestList.size

    fun updateData(newList: List<BloodRequest>) {
        requestList = newList
        notifyDataSetChanged()
    }
}
