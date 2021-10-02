package com.example.code.libs.location.api.permission

/**
 * Abstraction which allows to manage the permission check
 */
interface GeoLocationPermissionChecker {

  /**
   * @return True if the permission has been provided
   */
  val isPermissionGiven: Boolean
}
