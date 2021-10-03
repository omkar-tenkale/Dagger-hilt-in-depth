package com.example.code.ui.view.busstop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.code.R
import com.example.code.ui.events.OnItemSelectedListener

/**
 * The DiffUtil.ItemCallback for the BusStop ListAdapter
 */
val DIFF_UTIL = object : DiffUtil.ItemCallback<BusStopViewModel>() {

  override fun areItemsTheSame(
      oldItem: BusStopViewModel,
      newItem: BusStopViewModel
  ): Boolean {
    return oldItem.stopId == newItem.stopId
  }

  override fun areContentsTheSame(
      oldItem: BusStopViewModel,
      newItem: BusStopViewModel
  ): Boolean {
    return oldItem.stopId == newItem.stopId &&
        oldItem.stopDistance == newItem.stopDistance
  }
}

/**
 * The Adapter for the BusStopList
 */
class BusStopListAdapter(
    private val onItemSelectedListener: OnItemSelectedListener<BusStopViewModel>? = null
) :
    ListAdapter<BusStopViewModel, BusStopItemViewHolder>(DIFF_UTIL) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopItemViewHolder {
    val itemLayout =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.busstop_list_item_layout, parent, false)
    return BusStopItemViewHolder(
        itemLayout,
        onItemSelectedListener
    )
  }

  override fun onBindViewHolder(holder: BusStopItemViewHolder, position: Int) {
    holder.bind(position, getItem(position))
  }
}