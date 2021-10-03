package com.example.code.ui.view.busarrival

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.code.R

/**
 * The DiffUtil.ItemCallback for the BusStop ListAdapter
 */
val DIFF_UTIL = object : DiffUtil.ItemCallback<BusArrivalGroupViewModel>() {

  override fun areItemsTheSame(
      oldItem: BusArrivalGroupViewModel,
      newItem: BusArrivalGroupViewModel
  ): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(
      oldItem: BusArrivalGroupViewModel,
      newItem: BusArrivalGroupViewModel
  ): Boolean {
    return oldItem == newItem
  }
}

/**
 * The Adapter for the BusArrivals
 */
class BusArrivalListAdapter :
    ListAdapter<BusArrivalGroupViewModel, BusArrivalItemViewHolder>(DIFF_UTIL) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusArrivalItemViewHolder {
    val itemLayout =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.busarrival_list_item_layout, parent, false)
    return BusArrivalItemViewHolder(
        itemLayout
    )
  }

  override fun onBindViewHolder(holder: BusArrivalItemViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}