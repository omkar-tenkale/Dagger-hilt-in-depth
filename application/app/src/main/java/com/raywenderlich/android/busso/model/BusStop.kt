package com.raywenderlich.android.busso.model

import com.raywenderlich.android.location.api.model.GeoLocation

/**
 * The Model for the BusStop
 */
data class BusStop(
    val id: String,
    val name: String,
    val location: GeoLocation,
    val direction: String?,
    val indicator: String?,
    val distance: Float?
)