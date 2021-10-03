package com.example.code.ui.view.busstop

import android.app.Activity
import android.content.Context
import android.location.LocationManager
import androidx.fragment.app.Fragment
import com.example.code.libs.location.api.model.LocationEvent
import com.example.code.libs.location.api.permission.GeoLocationPermissionChecker
import com.example.code.libs.rx.provideRxLocationObservable
import com.example.code.libs.ui.navigation.Navigator
import com.example.code.libs.ui.navigation.NavigatorImpl
import io.reactivex.Observable

/**
 * This screen displays the list of bus stops
 */
class BusStopFragment : Fragment(){

    private lateinit var locationManager: LocationManager
    private lateinit var locationObservable: Observable<LocationEvent>
    private lateinit var navigator: Navigator

    private val grantedPermissionChecker = object : GeoLocationPermissionChecker {
        override val isPermissionGiven: Boolean = true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationObservable = provideRxLocationObservable(locationManager, grantedPermissionChecker)
        navigator = NavigatorImpl(context as Activity)
    }

}