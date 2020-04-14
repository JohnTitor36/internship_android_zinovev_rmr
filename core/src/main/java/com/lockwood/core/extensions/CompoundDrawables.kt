package com.lockwood.core.extensions

import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.widget.EditText
import android.widget.TextView

fun TextView.updateCompoundDrawables(
    start: Drawable? = null,
    top: Drawable? = null,
    end: Drawable? = null,
    bottom: Drawable? = null
) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
}

inline fun TextView.setOnEndDrawableClickListener(
    crossinline action: TextView.() -> Unit
) {
    setOnTouchListener { v, event ->
        if (event.x >= v.width - (v as TextView).totalPaddingRight) {
            if (event.action == MotionEvent.ACTION_UP) {
                action()
            }
            return@setOnTouchListener true
        }
        return@setOnTouchListener false
    }
}