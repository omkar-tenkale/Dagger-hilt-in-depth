package com.example.code

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.code.libs.ui.statusbar.MakeFullScreen


/**
 * Splash Screen is the first screen of the application. It opens with a screen & the icon at center
 * It actually opens in full screen mode -> It waits for 2 seconds and opens MainActivity
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MakeFullScreen().hideAllVersionsOfStatusBar(window,supportActionBar)
        setContentView(R.layout.activity_splash)

    }


}