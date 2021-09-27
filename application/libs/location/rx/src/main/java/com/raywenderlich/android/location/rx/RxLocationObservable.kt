package com.raywenderlich.android.location.rx

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.raywenderlich.android.location.api.model.*
import com.raywenderlich.android.location.api.permissions.GeoLocationPermissionChecker
import io.reactivex.Observable

/**
 * We want to create an RxObservable as hot observable for location data. We assume the permission
 * has been already requested because if not this emits the event
 */
@SuppressLint("MissingPermission")
fun provideRxLocationObservable(
  locationManager: LocationManager,
  geoLocationPermissionChecker: GeoLocationPermissionChecker,
  provider: String = LocationManager.GPS_PROVIDER,
  minTime: Long = 1000L,
  minDistance: Float = 100F
) =
  Observable.create<LocationEvent> { emitter ->
    // We check for the permission.
    if (geoLocationPermissionChecker.isPermissionGiven) {
      // We send an event about the permission
      emitter.onNext(LocationPermissionGranted(provider))
      // If last location is available we sent it
      val lastLocation = locationManager.getLastKnownLocation(provider)
      if (lastLocation != null) {
        emitter.onNext(
          LocationData(
            provider,
            GeoLocation(lastLocation.latitude, lastLocation.longitude)
          )
        )
      } else {
        emitter.onNext(LocationNotAvailable(provider))
      }
      locationManager.requestLocationUpdates(
        provider,
        minTime,
        minDistance,
        object : LocationListener {
          override fun onLocationChanged(location: Location) {
            if (location != null) {
              emitter.onNext(
                LocationData(
                  provider,
                  GeoLocation(location.latitude, location.longitude)
                )
              )
            } else {
              emitter.onNext(LocationNotAvailable(provider))
            }
          }

          override fun onStatusChanged(
            provider: String?,
            status: Int,
            extras: Bundle?
          ) {
            emitter.onNext(LocationStatus(provider, status, emptyMap()))
          }

          override fun onProviderEnabled(provider: String) {
            emitter.onNext(LocationProviderEnabledChanged(provider, true))
          }

          override fun onProviderDisabled(provider: String) {
            emitter.onNext(LocationProviderEnabledChanged(provider, false))
          }

        })
    } else {
      // If the permission is not given we need to generate a request for permission and then complete
      emitter.onNext(LocationPermissionRequest(provider))
      emitter.onComplete()
    }
  }
