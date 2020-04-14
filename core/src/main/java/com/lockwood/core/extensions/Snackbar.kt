package com.lockwood.core.extensions

import android.view.View
import androidx.annotation.ColorRes
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.lockwood.core.R

fun View.buildSnackbar(
    text: String,
    @ColorRes backgroundColor: Int = R.color.gray_light,
    @ColorRes textColor: Int = R.color.dark_blue
): Snackbar {
    return Snackbar.make(this, text, BaseTransientBottomBar.LENGTH_LONG).apply {
        setTextColor(context.color(textColor))
        view.setBackgroundColor(context.color(backgroundColor))
    }
}