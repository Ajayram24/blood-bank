package com.example.bloodbank

import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BloodGroupAdapter(
    private val groups: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<BloodGroupAdapter.GroupViewHolder>() {

    private var selectedPosition = 0

    inner class GroupViewHolder(val view: TextView) : RecyclerView.ViewHolder(view) {
        fun bind(group: String, isSelected: Boolean) {
            view.text = group
            view.setBackgroundResource(if (isSelected) R.drawable.bg_selected else R.drawable.bg_unselected)
            view.setOnClickListener {
                val prevPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(prevPosition)
                notifyItemChanged(adapterPosition)
                onItemClick(group)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val tv = TextView(parent.context).apply {
            layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                setMargins(8, 8, 8, 8)
            }
            setPadding(32, 16, 32, 16)
            textSize = 14f
            gravity = Gravity.CENTER
        }
        return GroupViewHolder(tv)
    }

    override fun getItemCount() = groups.size

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind(groups[position], position == selectedPosition)
    }
}
