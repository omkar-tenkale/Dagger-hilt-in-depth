package com.raywenderlich.android.location.api.model

/**
 * Location information which don't depend on the specific platform
 */
data class GeoLocation(
  val latitude: Double,
  val longitude: Double
)