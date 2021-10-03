package com.example.code.ui.view.busarrival

import com.example.code.ui.view.busstop.BusStopViewModel

/**
 * ViewModel for BusArrivals
 */
data class BusArrivalsViewModel(
    val busStop: BusStopViewModel,
    val arrivals: List<BusArrivalGroupViewModel>
)

/**
 * The Item for a group of arrivals in a bus stop for a line
 */
data class BusArrivalGroupViewModel(
    val lineName: String,
    val destination: String,
    val arrivals: List<BusArrivalViewModel> = emptyList()
)

/**
 * The Item for a single arrival for a line in a bus stop
 */
data class BusArrivalViewModel(
    val expectedTime: String,
    val vehicleId: String,
    val destination: String
)