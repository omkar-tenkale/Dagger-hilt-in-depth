package com.example.code.libs.ui.navigation

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Here we have the navigation for the screens
 * From single place here, we have the navigation logic required for the screens
 */
class NavigatorImpl(private val activity: Activity) : Navigator{

    override fun navigateTo(destination: Destination, params: Bundle?) {

        when (destination) {
            is ActivityBackDestination -> activityBackDestination(destination,params)
            is ActivityIntentDestination -> activityIntentDestination(destination,params)
            is FragmentBackDestination -> fragmentBackDestination(destination,params)
            is FragmentDestination<*> -> fragmentDestination(destination,params)
            is FragmentFactoryDestination<*> -> fragmentFactoryDestination(destination,params)
        }

    }

    private fun activityIntentDestination(destination: ActivityIntentDestination, params: Bundle?) {
        activity.startActivity(destination.intent)
    }

    private fun fragmentDestination(destination: FragmentDestination<*>, params: Bundle?) {
        val builder = (activity as AppCompatActivity).supportFragmentManager
            .beginTransaction()
            .replace(destination.anchorId, destination.fragment)
        destination.withBackStack?.run { builder.addToBackStack(this) }
        builder.commit()
    }

    private fun fragmentFactoryDestination(destination: FragmentFactoryDestination<*>, params: Bundle?) {
        val builder = (activity as AppCompatActivity).supportFragmentManager
            .beginTransaction()
            .replace(destination.anchorId, destination.fragmentFactory(destination.bundle))
        destination.withBackStack?.run { builder.addToBackStack(this) }
        builder.commit()
    }

    private fun activityBackDestination(destination: ActivityBackDestination, params: Bundle?) {
        activity.finish()
    }

    private fun fragmentBackDestination(destination: FragmentBackDestination, params: Bundle?) {
        (activity as AppCompatActivity).supportFragmentManager.popBackStack()
    }

}