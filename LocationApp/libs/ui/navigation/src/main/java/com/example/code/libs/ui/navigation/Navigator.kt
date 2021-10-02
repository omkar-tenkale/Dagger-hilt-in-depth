package com.example.code.libs.ui.navigation

import android.os.Bundle

/**
 * Abstraction responsible for navigating the screen to a specific destination
 */
interface Navigator {

    /**
     * This will allow us to navigate to specific destination
     * by passing some data in bundle
     */
    fun navigateTo(destination: Destination, params:Bundle? = null)

}