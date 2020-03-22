package com.lockwood.core.extensions

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.lockwood.core.R

fun View.buildSnackbar(
    text: String,
    actionText: String? = null,
    action: (() -> Unit)? = null,
    length: Int = BaseTransientBottomBar.LENGTH_SHORT
) = Snackbar.make(this, text, length).apply {
    if (action != null) {
        setAction(actionText) { action.invoke() }
    }
    setTextColor(context.color(R.color.gray_light))
    view.setBackgroundColor(context.color(R.color.dark_blue))
}