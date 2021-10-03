package com.example.code.ui.view.busarrival

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.code.R

/**
 * The DiffUtil.ItemCallback for the Arrival Time
 */
private val ARRIVAL_TIME_DIFF_UTIL = object : DiffUtil.ItemCallback<BusArrivalViewModel>() {

  override fun areItemsTheSame(
      oldItem: BusArrivalViewModel,
      newItem: BusArrivalViewModel
  ): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(
      oldItem: BusArrivalViewModel,
      newItem: BusArrivalViewModel
  ): Boolean {
    return oldItem == newItem
  }
}

/**
 * The Adapter for the BusArrivals
 */
class BusArrivalTimeAdapter :
    ListAdapter<BusArrivalViewModel, BusArrivalTimeViewHolder>(ARRIVAL_TIME_DIFF_UTIL) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusArrivalTimeViewHolder {
    val itemLayout =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.busarrival_item_layout, parent, false)
    return BusArrivalTimeViewHolder(
        itemLayout
    )
  }

  override fun onBindViewHolder(holder: BusArrivalTimeViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}