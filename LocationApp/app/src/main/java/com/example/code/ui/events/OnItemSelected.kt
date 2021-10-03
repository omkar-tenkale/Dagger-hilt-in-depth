package com.example.code.ui.events

/**
 * The callback we need to implement in order to manage the selection
 * of an item in a list or recyclerview. The first is the position and the
 * second the model for the selected item
 */
typealias OnItemSelectedListener<T> = (Int, T) -> Unit