package com.example.code.ui.view.busarrival

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.network.provideBussoEndPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * The Fragment for displaying the arrival time
 */
class BusArrivalFragment : Fragment() {

  private val disposables = CompositeDisposable()
  private lateinit var busArrivalRecyclerView: RecyclerView
  private val busArrivalsAdapter = BusArrivalListAdapter()

  private lateinit var busStopIndicator: TextView
  private lateinit var busStopName: TextView
  private lateinit var busStopDistance: TextView
  private lateinit var busStopDirection: TextView


  companion object {
    const val BUS_STOP_ID = "BUS_STOP_ID"
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_busarrival_layout, container, false).apply {
    busStopIndicator = findViewById(R.id.bus_stop_indicator)
    busStopName = findViewById(R.id.bus_stop_item_name)
    busStopDistance = findViewById(R.id.bus_stop_item_distance)
    busStopDirection = findViewById(R.id.bus_stop_item_direction)
    busArrivalRecyclerView = findViewById(R.id.busarrival_recyclerview)
    initRecyclerView(busArrivalRecyclerView)
  }

  private fun initRecyclerView(busStopRecyclerView: RecyclerView) {
    busArrivalRecyclerView.apply {
      val viewManager = LinearLayoutManager(busStopRecyclerView.context)
      layoutManager = viewManager
      adapter = busArrivalsAdapter
    }
  }

  override fun onStart() {
    super.onStart()
    getBusArrivals()
  }

  override fun onStop() {
    disposables.clear()
    super.onStop()
  }

  private fun getBusArrivals() {
    val busStopId = arguments?.getString(BUS_STOP_ID) ?: ""
    context?.let { ctx ->
      disposables.add(
          provideBussoEndPoint(ctx)
              .findArrivals(busStopId)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .map(::mapBusArrivals)
              .subscribe(::handleBusArrival, ::handleBusArrivalError)
      )
    }
  }

  private fun handleBusArrival(arrivals: BusArrivalsViewModel) {
    with(arrivals.busStop) {
      busStopIndicator.text = stopIndicator
      busStopName.text = stopName
      busStopDistance.text = stopDistance
      busStopDirection.text = stopDirection
    }
    busArrivalsAdapter.submitList(arrivals.arrivals)
  }

  private fun handleBusArrivalError(error: Throwable) {
    // TODO Handle errors
  }
}