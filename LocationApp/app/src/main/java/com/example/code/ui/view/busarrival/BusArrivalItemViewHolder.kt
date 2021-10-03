package com.example.code.ui.view.busarrival

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R

/**
 * The ViewHolder for the List of Arrivals for a BusStop
 */
class BusArrivalItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

  private val busArrivalLineName: TextView =
      itemView.findViewById(R.id.bus_arrival_line_and_destination)
  private val arrivalTimesAdapter = BusArrivalTimeAdapter()
  private val timesRecyclerView: RecyclerView =
      itemView.findViewById<RecyclerView>(R.id.bus_arrival_times_recyclerview).apply {
        val viewManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        layoutManager = viewManager
        adapter = arrivalTimesAdapter
      }


  lateinit var busStopListModel: BusArrivalGroupViewModel

  fun bind(itemViewModel: BusArrivalGroupViewModel) {
    busStopListModel = itemViewModel
    busArrivalLineName.text = "${busStopListModel.lineName} to ${busStopListModel.destination}"
    arrivalTimesAdapter.submitList(itemViewModel.arrivals)
  }
}