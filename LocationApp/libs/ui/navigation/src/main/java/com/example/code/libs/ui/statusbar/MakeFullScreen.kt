package com.example.code.libs.ui.statusbar

import android.annotation.TargetApi
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import java.lang.Exception

class MakeFullScreen {


    fun hideAllVersionsOfStatusBar(window: Window, supportActionBar: ActionBar?): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            hideDeprecatedStatusBar(window,supportActionBar)
        } else {
            hideActualStatusBar(window)
        }
    }


    private fun hideDeprecatedStatusBar(window: Window, supportActionBar: ActionBar?): Boolean {
        // 16...29
        return try {
            val decorView: View = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            supportActionBar?.hide()
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            true
        }catch (ex:Exception){
            ex.printStackTrace()
            false
        }
    }

    @TargetApi(Build.VERSION_CODES.R)
    private fun hideActualStatusBar(window: Window): Boolean {
        // >= 30
        return try {
            val decorView: View = window.decorView
            decorView.windowInsetsController?.hide(WindowInsets.Type.statusBars())
            true
        }catch (ex:Exception){
            ex.printStackTrace()
            false
        }
    }



}