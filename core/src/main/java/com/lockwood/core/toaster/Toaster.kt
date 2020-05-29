package com.lockwood.core.toaster

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import com.lockwood.core.R
import com.lockwood.core.extensions.color

class Toaster(private val context: Context) {

    companion object {

        @ColorRes
        private val DEFAULT_BACKGROUND_TINT = R.color.accent

        @ColorRes
        private val DEFAULT_TEXT_COLOR = R.color.light
        private const val DEFAULT_TOAST_LENGTH = Toast.LENGTH_SHORT
    }

    private var currentToast: Toast? = null

    fun toast(
        str: String,
        backgroundTint: Int = DEFAULT_BACKGROUND_TINT,
        textColor: Int = DEFAULT_TEXT_COLOR
    ) {
        currentToast?.cancel()
        buildToast(str, backgroundTint, textColor).show()
    }

    fun longToast(
        str: String,
        backgroundTint: Int = DEFAULT_BACKGROUND_TINT,
        textColor: Int = DEFAULT_TEXT_COLOR
    ) {
        currentToast?.cancel()
        buildToast(str, backgroundTint, textColor, Toast.LENGTH_LONG).show()
    }

    @SuppressLint("ShowToast")
    private fun buildToast(
        str: String,
        backgroundTint: Int = DEFAULT_BACKGROUND_TINT,
        textColor: Int = DEFAULT_TEXT_COLOR,
        length: Int = DEFAULT_TOAST_LENGTH
    ): Toast {
        val sourceToast = Toast.makeText(context, "", length)
        val view = sourceToast.view
        view.backgroundTintList = ColorStateList.valueOf(context.color(backgroundTint))

        val toastTextView = view.findViewById(android.R.id.message) as TextView
        with(toastTextView) {
            text = str
            setTextColor(context.color(textColor))
        }

        currentToast = sourceToast
        return sourceToast
    }

}