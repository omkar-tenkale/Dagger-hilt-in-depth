package com.example.code.di

import android.annotation.SuppressLint
import android.content.Context

/**
 * Names of the look up
 */
const val LOCATION_MANAGER = "LocationManager"

class ServiceLocatorImpl(
    val context: Context
) : ServiceLocator {
    @Suppress("UNCHECKED_CAST")
    @SuppressLint("ServiceCast")
    override fun <A : Any> lookUp(name: String): A = when (name) {
        LOCATION_MANAGER -> context.getSystemService(Context.LOCATION_SERVICE)
        else -> throw IllegalArgumentException("No component lookup for the key: $name")
    } as A
}