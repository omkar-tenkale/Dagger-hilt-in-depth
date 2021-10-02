package com.example.code.libs.rx

import android.annotation.SuppressLint
import android.location.LocationManager
import com.example.code.libs.location.api.model.*
import com.example.code.libs.location.api.permission.GeoLocationPermissionChecker
import io.reactivex.Observable


/**
 * At this point we always assume, permission is always available
 */
@SuppressLint("MissingPermission")
fun provideRxLocationObservable(
    locationManager: LocationManager,
    geoLocationPermissionChecker: GeoLocationPermissionChecker,
    provider: String = LocationManager.GPS_PROVIDER,
    minTime: Long = 1000L,
    minDistance: Float = 100F
) = Observable.create<LocationEvent> { emitter ->

    if (geoLocationPermissionChecker.isPermissionGiven) {
        // If the permission is given ---- Proceed

        // We send an event about the permission
        emitter.onNext(LocationPermissionRequest(provider))

        // Check if last location is available that we sent
        val lastLocation = locationManager.getLastKnownLocation(provider)

        if (lastLocation != null){
            // If the last location is available send that location
            emitter.onNext(
                LocationData(provider, GeoLocation(lastLocation.latitude,lastLocation.longitude))
            )
        }else{
            // If the last location is not available display send back location is not available
            emitter.onNext(
                LocationNotAvailable(provider)
            )
        }

        // Along with above - Request for new location --- Usually it takes time to update
        // We pass the criteria nd distance in the constructor
        locationManager.requestLocationUpdates(
            provider, minTime, minDistance
        ) {  location ->
            emitter.onNext(
                LocationData(provider, GeoLocation(location.latitude,location.longitude))
            )
        }

    }else{
        // If the permission is not given

        // Generate Request for permission
        /**
         * @param: its the provider used for next permission
         **/
        emitter.onNext(LocationPermissionRequest(provider))
        // Complete the observable
        emitter.onComplete()
    }



}