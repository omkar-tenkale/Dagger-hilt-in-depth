package com.example.code.ui.view.busarrival

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R

class BusArrivalTimeViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

  private val extectedTime = itemView.findViewById<TextView>(R.id.arrival_time)

  fun bind(arrival: BusArrivalViewModel) {
    extectedTime.text = arrival.expectedTime
  }
}