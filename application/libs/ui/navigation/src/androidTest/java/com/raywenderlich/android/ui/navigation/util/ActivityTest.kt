package com.raywenderlich.android.ui.navigation.util

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity to use in tests
 */
class ActivityTest : AppCompatActivity() {

    companion object {
        const val ANCHOR_POINT_ID = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val container = FrameLayout(this).apply {
            id = ANCHOR_POINT_ID
        }
        setContentView(container)
    }
}