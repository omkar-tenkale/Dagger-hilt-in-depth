package com.example.code

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.code.libs.ui.navigation.FragmentDestination
import com.example.code.libs.ui.navigation.Navigator
import com.example.code.libs.ui.navigation.NavigatorImpl
import com.example.code.ui.view.busstop.BusStopFragment

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
