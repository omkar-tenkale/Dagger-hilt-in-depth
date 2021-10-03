package com.example.code

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.code.di.GEO_PERMISSION_CHECKER
import com.example.code.di.LOCATION_MANAGER
import com.example.code.di.LOCATION_OBSERVABLE
import com.example.code.libs.location.api.model.LocationEvent
import com.example.code.libs.location.api.model.LocationPermissionGranted
import com.example.code.libs.location.api.model.LocationPermissionRequest
import com.example.code.libs.location.api.permission.GeoLocationPermissionChecker
import com.example.code.libs.rx.provideRxLocationObservable
import com.example.code.libs.ui.navigation.ActivityIntentDestination
import com.example.code.libs.ui.navigation.Navigator
import com.example.code.libs.ui.navigation.NavigatorImpl
import com.example.code.libs.ui.statusbar.MakeFullScreen
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit


/**
 * Splash Screen is the first screen of the application. It opens with a screen & the icon at center
 * It actually opens in full screen mode -> It waits for 2 seconds and opens MainActivity
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val DELAY_MILLIS = 1000L
        private const val LOCATION_PERMISSION_REQUEST_ID = 1
    }

    private lateinit var navigator: Navigator
    //private lateinit var locationManager: LocationManager
    private lateinit var locationObservable: Observable<LocationEvent>

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MakeFullScreen().hideAllVersionsOfStatusBar(window, supportActionBar)
        setContentView(R.layout.activity_splash)
        
        locationObservable = lookUp(LOCATION_OBSERVABLE) // HERE
        // Instantiate NavigatorImpl, passing reference to Activity as primary constructor parameter
        navigator = NavigatorImpl(this)

    }

    override fun onStart() {
        super.onStart()
        disposables.add(
            locationObservable
                .delay(DELAY_MILLIS, TimeUnit.MILLISECONDS)
                .filter(::isPermissionEvent)
                .subscribe(::handlePermissionRequest, ::handleError)
        )
    }

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }

    private fun handlePermissionRequest(locationEvent: LocationEvent?) {
        when (locationEvent) {
            is LocationPermissionRequest -> requestLocationPermission()
            is LocationPermissionGranted -> goToMain()
            else -> throw IllegalStateException("You should never receive this!")
        }
    }

    private fun goToMain() =
        Handler(Looper.myLooper()!!).post {
            navigator.navigateTo(
                ActivityIntentDestination(Intent(this, MainActivity::class.java))
            )
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_ID
            )
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_ID
            )
        }
    }


    private fun handleError(error: Throwable) {

    }

    private fun isPermissionEvent(locationEvent: LocationEvent): Boolean {
        return locationEvent is LocationPermissionRequest || locationEvent is LocationPermissionGranted
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_ID -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission granted! We go on!
                    goToMain()
                } else {
                    // Request denied, we request again
                    requestLocationPermission()
                }
            }
        }
    }

}