package com.example.code.di

/**
 * A ServiceLocator is a simple abstraction for any service that allows you to get the reference
 * to a specific object given its name.
 * *********************************************************************************************
 * Using ServiceLocator: Using its lookUp() operation to a lateinit var,
 * you’re not actually using injection. Rather, you’re using dependency lookup.
 */
interface ServiceLocator {
    /**
     * Returns the object of type A bound to a specific name
     */
    fun <A : Any> lookUp(name: String): A
}
