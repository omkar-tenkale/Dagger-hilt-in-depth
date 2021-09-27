package com.raywenderlich.android.ui.navigation

import android.os.Bundle


/**
 * Abstraction for the object responsible to navigate to a specific destination
 */
interface Navigator {

  /**
   * Allows to go to a specific destination passing some
   * parameters into a Bundle
   */
  fun navigateTo(destination: Destination, params: Bundle? = null)
}