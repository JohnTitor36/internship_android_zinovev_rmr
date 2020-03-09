package com.lockwood.core.toaster

import android.widget.Toast
import com.lockwood.core.R

interface Toaster {

    companion object {

        private val DEFAULT_BACKGROUND_TINT = R.color.accent
        private val DEFAULT_TEXT_COLOR = R.color.light
    }

    var currentToast: Toast?

    fun toast(
        str: String,
        backgroundTint: Int = DEFAULT_BACKGROUND_TINT,
        textColor: Int = DEFAULT_TEXT_COLOR
    )

    fun longToast(
        str: String,
        backgroundTint: Int = DEFAULT_BACKGROUND_TINT,
        textColor: Int = DEFAULT_TEXT_COLOR
    )

    fun buildToast(
        str: String,
        backgroundTint: Int = DEFAULT_BACKGROUND_TINT,
        textColor: Int = DEFAULT_TEXT_COLOR,
        length: Int = Toast.LENGTH_SHORT
    ): Toast

}
