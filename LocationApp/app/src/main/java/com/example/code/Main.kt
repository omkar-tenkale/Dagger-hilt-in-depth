package com.example.code

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.example.code.di.ServiceLocator
import com.example.code.di.ServiceLocatorImpl

class Main : Application() {
    // Reference to a ServiceLocator implementation.
    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        // Create an instance of ServiceLocatorImpl and assign it to the serviceLocator property.
        serviceLocator = ServiceLocatorImpl(this)
    }
}

/**
 * ---> Define the lookUp() extension function for AppCompatActivity
 * ---> Which allows you to easily look up components from any class
 * that IS-A AppCompatActivity, like SplashActivity.
 * */
internal fun <A: Any> AppCompatActivity.lookUp(name: String): A =
    (applicationContext as Main).serviceLocator.lookUp(name)
