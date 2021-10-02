package com.example.code.model

import com.example.code.libs.location.api.model.GeoLocation

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