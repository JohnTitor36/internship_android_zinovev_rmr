package com.lockwood.core.window

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.core.content.getSystemService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WindowManager @Inject constructor(
    private val context: Context
) {

    companion object {

        private const val DEFAULT_SCREEN_WIDTH = 0
    }

    private val windowManager
        get() = context.getSystemService<WindowManager>()

    val screenWidth: Int
        get() {
            val manager = windowManager
            return if (manager != null) {
                val displayMetrics = DisplayMetrics()
                manager.defaultDisplay.getMetrics(displayMetrics)
                displayMetrics.widthPixels
            } else {
                DEFAULT_SCREEN_WIDTH
            }
        }

}