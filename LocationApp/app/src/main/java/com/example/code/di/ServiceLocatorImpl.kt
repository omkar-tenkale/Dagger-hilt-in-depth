package com.example.code.di

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import com.example.code.libs.rx.provideRxLocationObservable
import com.example.code.permission.GeoLocationPermissionCheckerImpl

/** In a world without Dagger and Hilt, Service locator pattern helps us to create objects in a
 * Single place in the code and helps to get that reference with a look up operation,
 * The look up operation uses simple naming convention to identify the proper references */


/**
 * Names of the look up
 */
const val LOCATION_MANAGER = "LocationManager"
const val GEO_PERMISSION_CHECKER = "GeoPermissionChecker"
const val LOCATION_OBSERVABLE = "LocationObservable"


class ServiceLocatorImpl(
    val context: Context
) : ServiceLocator {

    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val geoLocationPermissionChecker = GeoLocationPermissionCheckerImpl(context)
    private val locationObservable =
        provideRxLocationObservable(locationManager, geoLocationPermissionChecker)

    @Suppress("UNCHECKED_CAST")
    @SuppressLint("ServiceCast")
    override fun <A : Any> lookUp(name: String): A = when (name) {
        LOCATION_MANAGER -> context.getSystemService(Context.LOCATION_SERVICE)
        GEO_PERMISSION_CHECKER -> GeoLocationPermissionCheckerImpl(context)
        LOCATION_OBSERVABLE -> locationObservable
        else -> throw IllegalArgumentException("No component lookup for the key: $name")
    } as A
}