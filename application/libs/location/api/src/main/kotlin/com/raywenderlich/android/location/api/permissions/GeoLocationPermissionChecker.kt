package com.raywenderlich.android.location.api.permissions

/**
 * Abstraction which allows to manage the permission check
 */
interface GeoLocationPermissionChecker {

  /**
   * @return True if the permission has been provided
   */
  val isPermissionGiven: Boolean
}
