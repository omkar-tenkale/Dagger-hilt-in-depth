package com.example.code.ui.view.busarrival

import com.example.code.model.BusArrival
import com.example.code.model.BusArrivalGroup
import com.example.code.model.BusArrivals
import com.example.code.ui.view.busstop.mapBusStop
import java.text.SimpleDateFormat
import java.util.*

/**
 * The ViewModel for the BusArrivals
 */
internal fun mapBusArrivals(busArrivals: BusArrivals): BusArrivalsViewModel =
    BusArrivalsViewModel(
        mapBusStop(busArrivals.busStop),
        busArrivals
            .arrivalGroups
            .map(::mapBusArrivalGroup)
    )

/**
 * Maps the BusArrivalGroup into a BusArrivalGroupViewModel adding some decorations
 */
internal fun mapBusArrivalGroup(busArrivalGroup: BusArrivalGroup): BusArrivalGroupViewModel {
  return BusArrivalGroupViewModel(
      lineName = busArrivalGroup.lineName,
      destination = busArrivalGroup.destination,
      arrivals = busArrivalGroup.arrivals.map(::mapBusArrival)
  )
}

/**
 * Maps the list of arrivals group into their viewmodel
 */
internal fun mapBusArrivalGroup(arrivals: List<BusArrivalGroup>): List<BusArrivalGroupViewModel> =
    arrivals.map(::mapBusArrivalGroup)

/**
 * Maps an arrival times group into its viewmodel
 */
internal fun mapBusArrival(arrival: BusArrival): BusArrivalViewModel =
    BusArrivalViewModel(
        expectedTime = expectedTime(arrival.expectedArrival),
        vehicleId = arrival.vehicleId ?: "-",
        destination = arrival.destinationName
    )

/**
 * Maps the list of arrival times group into their viewmodel
 */
fun mapBusArrival(arrivals: List<BusArrival>): List<BusArrivalViewModel> =
    arrivals.map(::mapBusArrival)

val DATE_FORMATTER = SimpleDateFormat("HH:mm")

private fun expectedTime(expectedTime: Date) = DATE_FORMATTER.format(expectedTime)
