package com.raywenderlich.android.location.rx

import android.content.Context
import com.raywenderlich.android.location.rx.robot.rxLocationTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(
  RobolectricTestRunner::class
)
class RxLocationObservableKtTest {

  lateinit var context: Context

  @Before
  fun setUp() {
    context = RuntimeEnvironment.systemContext
  }

  @Test
  fun whenPermissionIsDeniedLocationPermissionRequestIsSentAndThenCompletes() {
    rxLocationTest(context) {
      Given {
        permissionIsDenied()
      }
      When {
        subscribeRx()
      }
      Then {
        permissionRequestIsFired()
        isComplete()
      }
    }
  }

  @Test
  fun whenPermissionIsGrantedLocationPermissionRequestIsNotSent() {
    rxLocationTest(context) {
      Given {
        permissionIsGranted()
      }
      When {
        subscribeRx()
      }
      Then {
        noPermissionRequestIsFired()
      }
    }
  }

  @Test
  fun whenPermissionIsGrantedLocationPermissionGrantedIsSent() {
    rxLocationTest(context) {
      Given {
        permissionIsGranted()
      }
      When {
        subscribeRx()
      }
      Then {
        noPermissionRequestIsFired()
        permissionGrantedIsFired()
      }
    }
  }

  @Test
  fun whenPermissionIsGrantedNoLastLocationAvailableIsReceivedWithFirstLocation() {
    rxLocationTest(context) {
      Given {
        permissionIsGranted()
        noLastKnownLocationAvailable()
      }
      When {
        subscribeRx()
        locationChangedTo(location2())
      }
      Then {
        receivedLocationNotAvailable()
        containsLocation(location2().latitude, location2().longitude)
      }
    }
  }

  @Test
  fun whenPermissionIsGrantedLastLocationAvailableIsReturned() {
    rxLocationTest(context) {
      Given {
        permissionIsGranted()
        lastKnownLocationIs(location1(1000))
      }
      When {
        subscribeRx()
        locationChangedTo(location2(2000))
      }
      Then {
        containsLocation(location1().latitude, location1().longitude)
        containsLocation(location2().latitude, location2().longitude)
      }
    }
  }

  @Test
  fun whenProviderBecomesAvailableLocationProviderEnabledChangedIsFired() {
    rxLocationTest(context) {
      Given {
        permissionIsGranted()
        noLastKnownLocationAvailable()
      }
      When {
        subscribeRx()
        enableProvider()
      }
      Then {
        providerEnabledReceived()
      }
    }
  }

  @Test
  fun whenProviderBecomesNotAvailableLocationProviderDisabledChangedIsFired() {
    rxLocationTest(context) {
      Given {
        permissionIsGranted()
        noLastKnownLocationAvailable()
      }
      When {
        subscribeRx()
        disableProvider()
      }
      Then {
        providerDisabledReceived()
      }
    }
  }
}