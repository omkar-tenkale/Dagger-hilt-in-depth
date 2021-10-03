package com.example.code.ui.view.busstop

import com.example.code.model.BusStop

/**
 * Maps a BusStop into a BusStopViewModel with some decorations
 */
internal fun mapBusStop(busStop: BusStop): BusStopViewModel =
    BusStopViewModel(
        busStop.id,
        busStop.name,
        busStop.direction ?: "",
        busStop.indicator ?: "",
        stopDistance = formatDistance(busStop.distance)
    )

/**
 * Maps a List of BusStop into a List of BusStopViewModel with some decorations
 */
internal fun mapBusStop(busStops: List<BusStop>): List<BusStopViewModel> =
    busStops.map(::mapBusStop)

private fun formatDistance(distance: Float?) = if (distance != null) {
  "${distance.toInt()} m"
} else "--"