package com.example.code.ui.view.busstop

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.ui.events.OnItemSelectedListener

/**
 * The ViewHolder for the List of BusStop
 */
class BusStopItemViewHolder(
    itemView: View,
    private val onItemSelectedListener: OnItemSelectedListener<BusStopViewModel>? = null
) : RecyclerView.ViewHolder(itemView) {

  private val busStopNameTextView: TextView = itemView.findViewById(R.id.bus_stop_item_name)
  private val busStopIndicatorTextView: TextView = itemView.findViewById(R.id.bus_stop_indicator)
  private val busStopDirectionTextView: TextView =
      itemView.findViewById(R.id.bus_stop_item_direction)
  private val busStopDistanceTextView: TextView =
      itemView.findViewById(R.id.bus_stop_item_distance)

  lateinit var busStopListModel: BusStopViewModel

  fun bind(position: Int, itemViewModel: BusStopViewModel) {
    busStopListModel = itemViewModel
    busStopNameTextView.text = itemViewModel.stopName
    busStopIndicatorTextView.text = itemViewModel.stopIndicator
    busStopDistanceTextView.text = itemViewModel.stopDistance
    if (itemViewModel.stopDirection.isBlank()) {
      busStopDirectionTextView.visibility = View.GONE
    } else {
      busStopDirectionTextView.run {
        text = itemViewModel.stopDirection
        visibility = View.VISIBLE
      }
    }
    onItemSelectedListener?.run {
      itemView.setOnClickListener {
        invoke(position, itemViewModel)
      }
    }
  }
}