package com.raywenderlich.android.location.api.model

/**
 * This is the class which defines all the possible type of information you
 * can get from a LocationManager
 */
sealed class LocationEvent(val provider: String? = null)

/**
 * The case when we need to ask for permission in order to get data from here.
 */
class LocationPermissionRequest(provider: String?) : LocationEvent(provider)

/**
 * The case when we need to notify that the permission has been given. This can be
 * used to navigate to a different screen or to make some other decision
 */
class LocationPermissionGranted(provider: String?) : LocationEvent(provider)

/**
 * The location is not available because the provider needs some time. The location info
 * will be provided eventually.
 */
class LocationNotAvailable(provider: String?) : LocationEvent(provider)

/**
 * This encapsulate the location information. Location cannot be null otherwise the event would be
 * a LocationNotAvailable
 */
class LocationData(provider: String?, val location: GeoLocation) : LocationEvent(provider)

/**
 * This is emitted when we want to emit information about a change in status
 */
class LocationStatus(provider: String?, status: Int, val extras: Map<String, Any>) :
  LocationEvent(provider)

/**
 * This is emitted when the provided changes its state between enabled and disabled which is the
 * meaning of the second parameter
 */
class LocationProviderEnabledChanged(provider: String?, val enabled: Boolean) :
  LocationEvent(provider)
