package com.lockwood.core.extensions

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.lockwood.core.R

fun View.buildSnackbar(
    text: String
) = Snackbar.make(this, text, BaseTransientBottomBar.LENGTH_LONG).apply {
    setTextColor(context.color(R.color.dark_blue))
    view.setBackgroundColor(context.color(R.color.gray_light))
}