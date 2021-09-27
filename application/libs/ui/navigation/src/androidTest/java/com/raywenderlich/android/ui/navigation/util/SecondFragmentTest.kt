package com.raywenderlich.android.ui.navigation.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragmentTest : Fragment() {

    companion object {
        const val TEXTVIEW_ID = 777
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = TextView(container?.context).apply {
        id = TEXTVIEW_ID
        text = "SecondTestFragment"
    }
}