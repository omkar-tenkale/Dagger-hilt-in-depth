package com.raywenderlich.android.busso

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.busso.ui.view.busstop.BusStopFragment
import com.raywenderlich.android.ui.navigation.FragmentDestination
import com.raywenderlich.android.ui.navigation.Navigator
import com.raywenderlich.android.ui.navigation.NavigatorImpl

class MainActivity : AppCompatActivity() {

  private lateinit var navigator: Navigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navigator = NavigatorImpl(this)
    if (savedInstanceState == null) {
      navigator.navigateTo(FragmentDestination(BusStopFragment(), R.id.anchor_point))
    }
  }
}
